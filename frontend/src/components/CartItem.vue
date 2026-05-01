<template>
  <div class="cart-item">
    <div class="item-name">{{ item.name }}</div>
    <div class="item-controls">
      <span class="item-price">¥{{ item.price }}</span>
      <QuantityControl
        :quantity="item.quantity"
        @increase="$emit('increase', item)"
        @decrease="$emit('decrease', item)"
      />
    </div>
  </div>
</template>

<script setup>
import QuantityControl from './QuantityControl.vue'

defineProps({
  item: { type: Object, required: true }
})
defineEmits(['increase', 'decrease'])
</script>

<style scoped>
.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 0;
  border-bottom: 1px dashed #ebeef5;
}
.item-name { font-weight: bold; color: #303133; }
.item-controls { display: flex; align-items: center; gap: 15px; }
.item-price { color: #f56c6c; font-weight: bold; }
</style>
