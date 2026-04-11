import React, { useState, useEffect } from 'react';
import axios from 'axios';

const api = axios.create({
  baseURL: '/api',
});

export const adminApi = {
  getDashboard: () => api.get('/admin/dashboard'),
  getProducts: () => api.get('/admin/products'),
  getProduct: (id) => api.get(`/products/${id}`),
  saveProduct: (product) => api.post('/admin/products', product),
  deleteProduct: (id) => api.delete(`/admin/products/${id}`),
  getCategories: () => api.get('/admin/categories'),
  getCategory: (id) => api.get(`/admin/categories/${id}`),
  saveCategory: (category) => api.post('/admin/categories', category),
  deleteCategory: (id) => api.delete(`/admin/categories/${id}`),
  uploadFiles: (files) => {
    const formData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append('files', files[i]);
    }
    return api.post('/files/upload', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    });
  },
  getOrders: () => api.get('/admin/orders'),
  getOrderDetail: (id) => api.get(`/orders/${id}`),
  updateOrderStatus: (id, status) => api.put(`/admin/orders/${id}/status`, { status }),
  getCustomers: () => api.get('/admin/customers'),
  getCustomer: (id) => api.get(`/admin/customers/${id}`),
  saveCustomer: (user) => api.post('/admin/customers', user),
  deleteCustomer: (id) => api.delete(`/admin/customers/${id}`),
  getBlogs: () => api.get('/admin/blogs'),
  getBlog: (id) => api.get(`/blogs/${id}`),
  saveBlog: (blog) => api.post('/admin/blogs', blog),
  deleteBlog: (id) => api.delete(`/admin/blogs/${id}`),
  getBlogCategories: () => api.get('/admin/blog-categories'),
  saveBlogCategory: (category) => api.post('/admin/blog-categories', category),
  deleteBlogCategory: (id) => api.delete(`/admin/blog-categories/${id}`),
  getReviews: () => api.get('/admin/reviews'),
  deleteReview: (id) => api.delete(`/admin/reviews/${id}`),
  getBrewingGuides: () => api.get('/admin/brewing-guides'),
  saveBrewingGuide: (guide) => api.post('/admin/brewing-guides', guide),
  deleteBrewingGuide: (id) => api.delete(`/admin/brewing-guides/${id}`),
};

export const customerApi = {
  getProducts: (categoryId) => api.get('/products', { params: { categoryId } }),
  getProductDetail: (id) => api.get(`/products/${id}`),
  getCategories: () => api.get('/categories'),
  getBlogCategories: () => api.get('/blog-categories'),
  getBlogs: (categoryId) => api.get('/blogs', { params: { categoryId } }),
  getBlogDetail: (id) => api.get(`/blogs/${id}`),
  getProductReviews: (productId) => api.get(`/products/${productId}/reviews`),
  submitReview: (productId, review) => api.post(`/products/${productId}/reviews`, review),
  updateProfile: (userId, data) => api.put(`/users/${userId}`, data),
  getOrders: (userId) => api.get(`/orders/user/${userId}`),
  getOrderDetail: (id) => api.get(`/orders/${id}`),
  changePassword: (userId, data) => api.put(`/users/${userId}/change-password`, data),
  searchChatbot: (query, userId, lastProductId) => api.get('/chatbot', { params: { query, userId, lastProductId } }),
};


export const authApi = {
  login: (credentials) => api.post('/auth/login', credentials),
  register: (userData) => api.post('/auth/register', userData),
  resetPassword: (data) => api.post('/auth/reset-password', data)
};

export const provinceApi = {
  getProvinces: () => axios.get('https://provinces.open-api.vn/api/?depth=1'),
  getDistricts: (provinceCode) => axios.get(`https://provinces.open-api.vn/api/p/${provinceCode}?depth=2`),
  getWards: (districtCode) => axios.get(`https://provinces.open-api.vn/api/d/${districtCode}?depth=2`),
};

export default api;
