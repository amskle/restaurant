import { ref, computed } from 'vue'
import { submitOrder as apiSubmitOrder } from '@/api/order'

export function useCart() {
  const cart = ref([])

  const addToCart = (dish) => {
    const existingItem = cart.value.find(item => item.id === dish.id)
    if (existingItem) {
      existingItem.quantity++
    } else {
      cart.value.push({
        id: dish.id,
        name: dish.name,
        price: dish.price,
        quantity: 1
      })
    }
  }

  const increaseQty = (item) => {
    item.quantity++
  }

  const decreaseQty = (item) => {
    if (item.quantity > 1) {
      item.quantity--
    } else {
      const index = cart.value.findIndex(i => i.id === item.id)
      cart.value.splice(index, 1)
    }
  }

  const totalAmount = computed(() => {
    let total = 0
    cart.value.forEach(item => {
      total += item.price * item.quantity
    })
    return total.toFixed(2)
  })

  const submitOrder = async () => {
    try {
      const orderData = {
        tableNumber: 1,
        totalAmount: totalAmount.value,
        items: cart.value
      }
      const response = await apiSubmitOrder(orderData)
      alert(response.data)
      cart.value = []
    } catch (error) {
      console.error('提交订单失败', error)
      alert('提交订单失败，请检查后端服务！')
    }
  }

  return { cart, addToCart, increaseQty, decreaseQty, totalAmount, submitOrder }
}
