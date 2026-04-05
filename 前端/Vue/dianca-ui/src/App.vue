<template>
  <div class="app-container">
    <header class="header">
      <h1>🍽️ 餐厅收银点餐系统</h1>
    </header>

    <div class="main-content">
      <div class="menu-container">
        <div v-for="category in categories" :key="category.id" class="category-section">
          <h2 class="category-title">{{ category.name }}</h2>
          <div class="dish-grid">
            <div v-for="dish in getDishesByCategory(category.id)" :key="dish.id" class="dish-card">
              <div class="dish-info">
                <h3 class="dish-name">{{ dish.name }}</h3>
                <p class="dish-desc">{{ dish.description }}</p>
              </div>
              <div class="dish-action">
                <span class="dish-price">¥{{ dish.price }}</span>
                <button class="add-btn" @click="addToCart(dish)">+ 点菜</button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="cart-container">
        <h2>🧾 当前订单 (1号桌)</h2>
        
        <div class="cart-items">
          <div v-if="cart.length === 0" class="empty-cart">
            还没有点菜，请从左侧选择菜品。
          </div>
          
          <div v-for="(item, index) in cart" :key="index" class="cart-item">
            <div class="item-name">{{ item.name }}</div>
            <div class="item-controls">
              <span class="item-price">¥{{ item.price }}</span>
              <div class="quantity-control">
                <button @click="decreaseQty(item)">-</button>
                <span>{{ item.quantity }}</span>
                <button @click="increaseQty(item)">+</button>
              </div>
            </div>
          </div>
        </div>

        <div class="cart-footer">
          <div class="total-row">
            <span>总计：</span>
            <span class="total-price">¥{{ totalAmount }}</span>
          </div>
          <button class="submit-btn" :disabled="cart.length === 0" @click="submitOrder">
            提交订单
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'

// --- 菜单数据逻辑 ---
const categories = ref([])
const dishes = ref([])

const fetchMenuData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/menu/list')
    categories.value = response.data.categories
    dishes.value = response.data.dishes
  } catch (error) {
    console.error('获取菜单失败', error)
  }
}

const getDishesByCategory = (categoryId) => {
  return dishes.value.filter(dish => dish.categoryId === categoryId)
}

// --- 购物车逻辑 ---
const cart = ref([]) // 存放在购物车里的菜品

// 1. 添加到购物车
const addToCart = (dish) => {
  // 检查购物车里是不是已经有这道菜了
  const existingItem = cart.value.find(item => item.id === dish.id)
  if (existingItem) {
    existingItem.quantity++ // 有的话数量+1
  } else {
    // 没有的话，把菜品信息复制一份，并加上数量属性 push 进去
    cart.value.push({
      id: dish.id,
      name: dish.name,
      price: dish.price,
      quantity: 1
    })
  }
}

// 2. 增加数量
const increaseQty = (item) => {
  item.quantity++
}

// 3. 减少数量 (如果减到0就从购物车移除)
const decreaseQty = (item) => {
  if (item.quantity > 1) {
    item.quantity--
  } else {
    const index = cart.value.findIndex(i => i.id === item.id)
    cart.value.splice(index, 1)
  }
}

// 4. 计算总价 (Vue 的 computed 特性，当 cart 变化时自动重新计算)
const totalAmount = computed(() => {
  let total = 0
  cart.value.forEach(item => {
    total += item.price * item.quantity
  })
  return total.toFixed(2) // 保留两位小数
})


// 5. 真实提交订单到后端
const submitOrder = async () => {
  try {
    // 组装要发给后端的数据结构
    const orderData = {
      tableNumber: 1, // 假设当前是1号桌
      totalAmount: totalAmount.value,
      items: cart.value  // 直接把购物车数组传过去
    }

    // 发送 POST 请求
    const response = await axios.post('http://localhost:8080/api/orders/submit', orderData)
    
    alert(response.data) // 显示后端返回的成功信息和订单号
    
    // 提交成功后清空购物车
    cart.value = []
    
  } catch (error) {
    console.error('提交订单失败', error)
    alert('提交订单失败，请检查后端服务！')
  }
}

onMounted(() => {
  fetchMenuData()
})
</script>

<style scoped>
/* 样式更新，增加了左右分栏的布局 */
.app-container {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background-color: #f5f7fa;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
.header {
  background-color: #409eff;
  color: white;
  padding: 15px 20px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  z-index: 10;
}
.header h1 {
  margin: 0;
  font-size: 22px;
}

/* 主体分左右两栏 */
.main-content {
  display: flex;
  flex: 1;
  overflow: hidden;
}

/* 左侧菜单 */
.menu-container {
  flex: 7; /* 占据 7 份宽度 */
  padding: 20px;
  overflow-y: auto;
}
.category-title {
  border-left: 5px solid #409eff;
  padding-left: 10px;
  margin-top: 20px;
  color: #303133;
}
.dish-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  gap: 15px;
  margin-top: 15px;
}
.dish-card {
  background: white;
  border-radius: 8px;
  padding: 15px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.05);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.dish-name { margin: 0 0 5px 0; color: #303133; font-size: 16px; }
.dish-desc { color: #909399; font-size: 12px; margin: 0 0 15px 0; }
.dish-action {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-top: 1px dashed #ebeef5;
  padding-top: 10px;
}
.dish-price { color: #f56c6c; font-size: 18px; font-weight: bold; }
.add-btn { background-color: #409eff; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; }
.add-btn:hover { background-color: #66b1ff; }

/* 右侧购物车 */
.cart-container {
  flex: 3; /* 占据 3 份宽度 */
  background: white;
  border-left: 1px solid #ebeef5;
  display: flex;
  flex-direction: column;
  padding: 20px;
  box-shadow: -2px 0 8px rgba(0,0,0,0.05);
}
.cart-container h2 {
  margin-top: 0;
  padding-bottom: 15px;
  border-bottom: 1px solid #ebeef5;
  font-size: 18px;
}
.cart-items {
  flex: 1;
  overflow-y: auto;
  margin-bottom: 20px;
}
.empty-cart {
  text-align: center;
  color: #909399;
  margin-top: 50px;
}
.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0;
  border-bottom: 1px dashed #ebeef5;
}
.item-name { font-weight: bold; color: #303133; }
.item-controls { display: flex; align-items: center; gap: 15px; }
.item-price { color: #f56c6c; font-weight: bold;}
.quantity-control {
  display: flex;
  align-items: center;
  gap: 10px;
}
.quantity-control button {
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: 1px solid #dcdfe6;
  background: white;
  cursor: pointer;
}
.quantity-control button:hover { background: #f5f7fa; color: #409eff; border-color: #409eff;}

/* 底部结算 */
.cart-footer {
  padding-top: 15px;
  border-top: 2px solid #ebeef5;
}
.total-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 15px;
}
.total-price { color: #f56c6c; font-size: 24px; }
.submit-btn {
  width: 100%;
  background-color: #67c23a;
  color: white;
  border: none;
  padding: 12px;
  font-size: 16px;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}
.submit-btn:disabled {
  background-color: #a0cfff;
  cursor: not-allowed;
}
.submit-btn:hover:not(:disabled) {
  background-color: #85ce61;
}
</style>