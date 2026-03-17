import React, { useState, useEffect } from 'react';
import { adminApi } from '../../api/api';
import { ShoppingCart, Search, Filter, Eye, CheckCircle2, Clock, Truck, ChevronDown, MoreHorizontal } from 'lucide-react';

const AdminOrders = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    adminApi.getOrders()
      .then(res => {
        setOrders(res.data);
        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        setLoading(false);
      });
  }, []);

  const getStatusStyle = (status) => {
    switch (status) {
      case 'Completed': return 'bg-emerald-100 text-emerald-600 border-emerald-200';
      case 'Pending': return 'bg-amber-100 text-amber-600 border-amber-200';
      case 'Processing': return 'bg-blue-100 text-blue-600 border-blue-200';
      case 'Cancelled': return 'bg-rose-100 text-rose-600 border-rose-200';
      default: return 'bg-slate-100 text-slate-600 border-slate-200';
    }
  };

  const getStatusIcon = (status) => {
    switch (status) {
      case 'Completed': return CheckCircle2;
      case 'Pending': return Clock;
      case 'Processing': return Truck;
      default: return Clock;
    }
  };

  if (loading) return (
    <div className="flex items-center justify-center min-h-[400px]">
      <div className="animate-spin size-8 border-4 border-primary border-t-transparent rounded-full"></div>
    </div>
  );

  return (
    <div className="space-y-8">
      <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">
        <div>
          <h2 className="text-3xl font-black tracking-tighter text-slate-900 dark:text-white">Quản lý đơn hàng</h2>
          <p className="text-slate-500 font-medium">Theo dõi và cập nhật trạng thái các đơn đặt hàng từ khách hàng.</p>
        </div>
        <div className="flex items-center gap-3">
           <div className="px-4 py-2 bg-white dark:bg-slate-900 border border-slate-100 dark:border-slate-800 rounded-2xl flex items-center gap-3 shadow-sm hover:border-primary/30 transition-all cursor-pointer group">
              <span className="text-[10px] font-black uppercase tracking-widest text-slate-400 group-hover:text-primary transition-colors">7 Đơn hàng chờ duyệt</span>
              <div className="size-2 bg-rose-500 rounded-full animate-ping"></div>
           </div>
        </div>
      </div>

      <div className="bg-white dark:bg-slate-900 rounded-3xl border border-slate-100 dark:border-slate-800 shadow-sm overflow-hidden">
        <div className="p-4 border-b border-slate-50 dark:border-slate-800 flex flex-col lg:flex-row gap-4">
           <div className="flex-1 relative">
              <Search className="absolute left-4 top-1/2 -translate-y-1/2 size-4 text-slate-400" />
              <input type="text" placeholder="Tìm theo Mã đơn, Tên KH hoặc SĐT..." className="w-full pl-10 pr-4 py-2.5 rounded-xl bg-slate-50 dark:bg-slate-800 border-none outline-none text-sm focus:ring-4 focus:ring-primary/5 transition-all" />
           </div>
           <div className="flex gap-4">
              <div className="relative group">
                 <select className="pl-6 pr-10 py-2.5 rounded-xl bg-slate-50 dark:bg-slate-800 border-none outline-none text-[10px] font-black uppercase tracking-widest appearance-none cursor-pointer focus:ring-4 focus:ring-primary/5 min-w-[150px]">
                    <option>Trạng thái</option>
                    <option>Chờ duyệt</option>
                    <option>Đang giao</option>
                    <option>Hoàn thành</option>
                 </select>
                 <ChevronDown className="absolute right-4 top-1/2 -translate-y-1/2 size-3 text-slate-400 pointer-events-none" />
              </div>
              <button className="flex items-center gap-2 px-6 py-2.5 bg-slate-900 text-white rounded-xl text-[10px] font-bold uppercase tracking-widest hover:scale-105 transition-all">
                 <Filter className="size-3" />
                 Lọc nhanh
              </button>
           </div>
        </div>

        <div className="overflow-x-auto">
          <table className="w-full text-left">
            <thead>
              <tr className="bg-slate-50/50 dark:bg-slate-800/30 whitespace-nowrap">
                <th className="px-6 py-5 text-[10px] font-black text-slate-400 uppercase tracking-[0.15em]">Mã đơn</th>
                <th className="px-6 py-5 text-[10px] font-black text-slate-400 uppercase tracking-[0.15em]">Khách hàng</th>
                <th className="px-6 py-5 text-[10px] font-black text-slate-400 uppercase tracking-[0.15em]">Thanh toán</th>
                <th className="px-6 py-5 text-[10px] font-black text-slate-400 uppercase tracking-[0.15em]">Tổng tiền</th>
                <th className="px-6 py-5 text-[10px] font-black text-slate-400 uppercase tracking-[0.15em]">Trạng thái</th>
                <th className="px-6 py-5 text-[10px] font-black text-slate-400 uppercase tracking-[0.15em] text-right">Chi tiết</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-50 dark:divide-slate-800">
              {orders.map((order) => {
                const StatusIcon = getStatusIcon(order.status);
                return (
                  <tr key={order.id} className="hover:bg-slate-50/50 dark:hover:bg-slate-800/50 transition-colors group">
                    <td className="px-6 py-5 space-y-1">
                      <p className="font-bold text-sm text-primary">#TRT-{order.id.toString().padStart(4, '0')}</p>
                      <p className="text-[10px] text-slate-400 font-bold uppercase italic">{new Date().toLocaleDateString('vi-VN')}</p>
                    </td>
                    <td className="px-6 py-5">
                      <div className="flex items-center gap-3">
                         <div className="size-9 rounded-full bg-slate-100 flex items-center justify-center font-black text-xs text-slate-500 uppercase">
                            {(order.user?.fullname || 'AD')[0]}
                         </div>
                         <div>
                            <p className="font-bold text-sm text-slate-900 dark:text-white leading-tight">{order.user?.fullname || 'Ẩn danh'}</p>
                            <p className="text-[10px] text-slate-400 font-medium">{order.user?.phone || 'Chưa cập nhật'}</p>
                         </div>
                      </div>
                    </td>
                    <td className="px-6 py-5">
                       <span className="text-[10px] font-bold text-slate-500 bg-slate-100 px-2 py-1 rounded uppercase tracking-widest">{order.paymentMethod || 'COD'}</span>
                    </td>
                    <td className="px-6 py-5 font-black text-sm text-slate-700 dark:text-slate-300">
                       {(order.totalAmount || 0).toLocaleString()}đ
                    </td>
                    <td className="px-6 py-5">
                       <div className={`inline-flex items-center gap-1.5 px-3 py-1 rounded-full border text-[10px] font-black uppercase tracking-widest ${getStatusStyle(order.status)}`}>
                          <StatusIcon className="size-3" />
                          {order.status}
                       </div>
                    </td>
                    <td className="px-6 py-5 text-right">
                       <button className="p-2 text-slate-400 hover:text-primary hover:bg-primary/5 rounded-xl transition-all group-hover:scale-110">
                          <Eye className="size-5" />
                       </button>
                    </td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default AdminOrders;
