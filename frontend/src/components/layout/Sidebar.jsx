import React from 'react';
import { NavLink, Link } from 'react-router-dom';
import { 
  LayoutDashboard, 
  Package, 
  Layers, 
  ShoppingCart, 
  Users, 
  BookOpen, 
  Tag, 
  Star, 
  Info,
  Home,
  ChevronRight,
  MapPin
} from 'lucide-react';

const SidebarItem = ({ to, icon: Icon, label }) => (
  <NavLink
    to={to}
    end={to === '/admin'}
    className={({ isActive }) => `
      flex items-center justify-between px-4 py-3 rounded-2xl transition-all duration-300 group
      ${isActive 
        ? 'bg-primary text-white shadow-lg shadow-primary/20 translate-x-1' 
        : 'text-slate-500 hover:bg-slate-50 dark:hover:bg-slate-800 hover:text-slate-900 dark:hover:text-white'
      }
    `}
  >
    <div className="flex items-center gap-3">
      <Icon className={`size-5 transition-transform duration-300 group-hover:scale-110`} />
      <span className="font-bold text-sm tracking-tight">{label}</span>
    </div>
    <ChevronRight className={`size-4 opacity-0 transition-opacity group-hover:opacity-100`} />
  </NavLink>
);

const Sidebar = () => {
  const menuGroups = [
    {
      title: "Chính",
      items: [
        { to: "/admin", icon: LayoutDashboard, label: "Tổng quan" }
      ]
    },
    {
      title: "Quản lý cửa hàng",
      items: [
        { to: "/admin/products", icon: Package, label: "Sản phẩm" },
        { to: "/admin/categories", icon: Layers, label: "Danh mục" },
        { to: "/admin/orders", icon: ShoppingCart, label: "Đơn hàng" },
        { to: "/admin/customers", icon: Users, label: "Khách hàng" }
      ]
    },
    {
      title: "Nội dung & Phân tích",
      items: [
        { to: "/admin/blogs", icon: BookOpen, label: "Bài viết" },
        { to: "/admin/blog-categories", icon: Tag, label: "Danh mục Blog" },
        { to: "/admin/reviews", icon: Star, label: "Đánh giá" },
        { to: "/admin/brewing-guides", icon: MapPin, label: "Hướng dẫn pha" }
      ]
    }
  ];

  return (
    <aside className="w-72 bg-white dark:bg-slate-900 border-r border-slate-100 dark:border-slate-800 flex flex-col z-20">
      <div className="p-8 pb-4">
        <Link to="/" className="flex items-center gap-3 group">
          <span className="material-symbols-outlined text-4xl text-primary group-hover:scale-110 transition-transform">spa</span>
          <h1 className="text-2xl font-black tracking-tight uppercase text-primary transition-all">
            Trà Thơm
          </h1>
        </Link>
      </div>

      <nav className="flex-1 px-4 py-4 space-y-8 overflow-y-auto">
        {menuGroups.map((group, idx) => (
          <div key={idx} className="space-y-2">
            <p className="px-4 text-[10px] font-bold text-slate-400 uppercase tracking-widest h-4 flex items-center">
              {group.title}
            </p>
            <div className="space-y-1">
              {group.items.map((item, idy) => (
                <SidebarItem key={idy} {...item} />
              ))}
            </div>
          </div>
        ))}
      </nav>

      <div className="p-4 border-t border-slate-100 dark:border-slate-800">
        <Link 
          to="/" 
          className="flex items-center gap-3 px-4 py-3 rounded-2xl text-slate-500 hover:bg-rose-50 hover:text-rose-600 dark:hover:bg-rose-900/10 transition-all font-bold text-sm"
        >
          <Home className="size-5" />
          Quay lại cửa hàng
        </Link>
      </div>
    </aside>
  );
};

export default Sidebar;
