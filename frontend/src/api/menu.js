import request from './request'

export function getMenu() {
  return request.get('/api/menu/list')
}
