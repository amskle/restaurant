<template>
  <div class="app-container">
    <AppHeader />

    <div class="main-content">
      <div class="menu-container">
        <div v-for="category in categories" :key="category.id" class="category-section">
          <h2 class="category-title">{{ category.name }}</h2>
          <div class="dish-grid">
            <DishCard
              v-for="dish in getDishesByCategory(category.id)"
              :key="dish.id"
              :dish="dish"
              @add="addToCart"
            />
          </div>
        </div>
      </div>

      <CartSidebar
        :cart="cart"
        :totalAmount="totalAmount"
        @increase="increaseQty"
        @decrease="decreaseQty"
        @submit="submitOrder"
      />
    </div>
  </div>
</template>

<script setup>
import AppHeader from '@/components/AppHeader.vue'
import DishCard from '@/components/DishCard.vue'
import CartSidebar from '@/components/CartSidebar.vue'
import { useMenu } from '@/composables/useMenu'
import { useCart } from '@/composables/useCart'

const { categories, getDishesByCategory } = useMenu()
const { cart, addToCart, increaseQty, decreaseQty, totalAmount, submitOrder } = useCart()
</script>

<style scoped>
.app-container {
  background-color: #f5f7fa;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
.main-content {
  display: flex;
  flex: 1;
  overflow: hidden;
}
.menu-container {
  flex: 7;
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
</style>
