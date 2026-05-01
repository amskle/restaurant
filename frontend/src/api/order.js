import request from './request'

export function submitOrder(orderData) {
  return request.post('/api/orders/submit', orderData)
}
