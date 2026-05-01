<template>
  <div class="quantity-control">
    <button @click="$emit('decrease')">-</button>
    <span>{{ quantity }}</span>
    <button @click="$emit('increase')">+</button>
  </div>
</template>

<script setup>
defineProps({
  quantity: { type: Number, required: true }
})
defineEmits(['increase', 'decrease'])
</script>

<style scoped>
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
.quantity-control button:hover { background: #f5f7fa; color: #409eff; border-color: #409eff; }
</style>
