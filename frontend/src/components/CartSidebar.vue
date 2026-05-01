<template>
  <div class="cart-container">
    <h2>🧾 当前订单 (1号桌)</h2>

    <div class="cart-items">
      <div v-if="cart.length === 0" class="empty-cart">
        还没有点菜，请从左侧选择菜品。
      </div>

      <CartItem
        v-for="(item, index) in cart"
        :key="index"
        :item="item"
        @increase="$emit('increase', item)"
        @decrease="$emit('decrease', item)"
      />
    </div>

    <div class="cart-footer">
      <div class="total-row">
        <span>总计：</span>
        <span class="total-price">¥{{ totalAmount }}</span>
      </div>
      <button class="submit-btn" :disabled="cart.length === 0" @click="$emit('submit')">
        提交订单
      </button>
    </div>
  </div>
</template>

<script setup>
import CartItem from './CartItem.vue'

defineProps({
  cart: { type: Array, required: true },
  totalAmount: { type: String, required: true }
})
defineEmits(['increase', 'decrease', 'submit'])
</script>

<style scoped>
.cart-container {
  flex: 3;
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
