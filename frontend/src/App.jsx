import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainLayout from './components/layout/MainLayout';
import AdminLayout from './components/layout/AdminLayout';

// Customer Pages
import Home from './pages/customer/Home';
import Products from './pages/customer/Products';
import ProductDetail from './pages/customer/ProductDetail';
import Cart from './pages/customer/Cart';
import Login from './pages/customer/Login';
import Story from './pages/customer/Story';
import Checkout from './pages/customer/Checkout';
import Profile from './pages/customer/Profile';
import OrderDetail from './pages/customer/OrderDetail';
import Blog from './pages/customer/Blog';
import BlogDetail from './pages/customer/BlogDetail';

// Admin Pages
import Dashboard from './pages/admin/Dashboard';
import AdminProducts from './pages/admin/AdminProducts';
import AdminProductForm from './pages/admin/AdminProductForm';
import AdminProductView from './pages/admin/AdminProductView';
import AdminCategories from './pages/admin/AdminCategories';
import AdminCategoryForm from './pages/admin/AdminCategoryForm';
import AdminOrders from './pages/admin/AdminOrders';
import AdminCustomers from './pages/admin/AdminCustomers';
import AdminCustomerForm from './pages/admin/AdminCustomerForm';
import AdminBlogs from './pages/admin/AdminBlogs';
import AdminBlogCategories from './pages/admin/AdminBlogCategories';
import AdminReviews from './pages/admin/AdminReviews';
import AdminBrewingGuides from './pages/admin/AdminBrewingGuides';
import AdminBrewingGuideForm from './pages/admin/AdminBrewingGuideForm';

import { CartProvider } from './context/CartContext';

function App() {
  return (
    <CartProvider>
      <Router>
        <Routes>
          {/* Customer Routes */}
          <Route path="/" element={<MainLayout />}>
            <Route index element={<Home />} />
            <Route path="products" element={<Products />} />
            <Route path="product/:id" element={<ProductDetail />} />
            <Route path="cart" element={<Cart />} />
            <Route path="checkout" element={<Checkout />} />
            <Route path="login" element={<Login />} />
            <Route path="story" element={<Story />} />
            <Route path="profile" element={<Profile />} />
            <Route path="order/:id" element={<OrderDetail />} />
            <Route path="blog" element={<Blog />} />
            <Route path="blog/:id" element={<BlogDetail />} />
          </Route>

          {/* Admin Routes */}
          <Route path="/admin" element={<AdminLayout />}>
            <Route index element={<Dashboard />} />
            <Route path="products" element={<AdminProducts />} />
            <Route path="products/new" element={<AdminProductForm />} />
            <Route path="products/:id" element={<AdminProductView />} />
            <Route path="products/:id/edit" element={<AdminProductForm />} />
            <Route path="categories" element={<AdminCategories />} />
            <Route path="categories/new" element={<AdminCategoryForm />} />
            <Route path="categories/:id/edit" element={<AdminCategoryForm />} />
            <Route path="orders" element={<AdminOrders />} />
            <Route path="customers" element={<AdminCustomers />} />
            <Route path="customers/new" element={<AdminCustomerForm />} />
            <Route path="customers/:id/edit" element={<AdminCustomerForm />} />
            <Route path="blogs" element={<AdminBlogs />} />
            <Route path="blog-categories" element={<AdminBlogCategories />} />
            <Route path="reviews" element={<AdminReviews />} />
            <Route path="brewing-guides" element={<AdminBrewingGuides />} />
            <Route path="brewing-guides/new" element={<AdminBrewingGuideForm />} />
            <Route path="brewing-guides/:id/edit" element={<AdminBrewingGuideForm />} />
          </Route>
        </Routes>
      </Router>
    </CartProvider>
  );
}

export default App;
