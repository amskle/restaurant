import { createRouter, createWebHistory } from 'vue-router'
import PosView from '@/views/PosView.vue'

const routes = [
  { path: '/', name: 'Pos', component: PosView }
]

export default createRouter({ history: createWebHistory(), routes })
