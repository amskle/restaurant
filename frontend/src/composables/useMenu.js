import { ref, onMounted } from 'vue'
import { getMenu } from '@/api/menu'

export function useMenu() {
  const categories = ref([])
  const dishes = ref([])

  const fetchMenuData = async () => {
    try {
      const response = await getMenu()
      categories.value = response.data.categories
      dishes.value = response.data.dishes
    } catch (error) {
      console.error('获取菜单失败', error)
    }
  }

  const getDishesByCategory = (categoryId) => {
    return dishes.value.filter(dish => dish.categoryId === categoryId)
  }

  onMounted(() => {
    fetchMenuData()
  })

  return { categories, dishes, getDishesByCategory }
}
