<template>
  <div class="dish-card">
    <div class="dish-info">
      <h3 class="dish-name">{{ dish.name }}</h3>
      <p class="dish-desc">{{ dish.description }}</p>
    </div>
    <div class="dish-action">
      <span class="dish-price">¥{{ dish.price }}</span>
      <button class="add-btn" @click="$emit('add', dish)">+ 点菜</button>
    </div>
  </div>
</template>

<script setup>
defineProps({
  dish: { type: Object, required: true }
})
defineEmits(['add'])
</script>

<style scoped>
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
</style>
