import React, { useState, useEffect } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { 
  ChevronLeft, 
  Package, 
  Truck, 
  MapPin, 
  CreditCard, 
  CheckCircle2, 
  Clock, 
  XCircle,
  Calendar,
  Phone,
  User
} from 'lucide-react';
import { adminApi } from '../../api/api'; // Using adminApi getProduct if needed or generic

const OrderDetail = () => {
  const { id } = useParams();
  const [order, setOrder] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    // Note: In a real app, we'd have customerApi.getOrderDetail(id)
    // For now, let's fetch from generic or mock if API not ready
    fetchOrderDetail();
  }, [id]);

  const fetchOrderDetail = async () => {
    try {
      // Assuming we can use a generic endpoint or a new one
      const res = await adminApi.getOrders(); // This is a bit heavy, filter for now
      const currentOrder = res.data.find(o => o.id === parseInt(id));
      if (currentOrder) {
        setOrder(currentOrder);
      } else {
        alert('Không tìm thấy đơn hàng!');
        navigate('/profile');
      }
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return (
    <div className="flex items-center justify-center min-h-screen">
      <div className="animate-spin size-8 border-4 border-[#2d5a27] border-t-transparent rounded-full"></div>
    </div>
  );

  if (!order) return null;

  const statusMap = {
    'PENDING': { label: 'Chờ xử lý', color: 'text-amber-600', bg: 'bg-amber-50', icon: Clock },
    'CONFIRMED': { label: 'Đã xác nhận', color: 'text-blue-600', bg: 'bg-blue-50', icon: CheckCircle2 },
    'SHIPPING': { label: 'Đang giao hàng', color: 'text-indigo-600', bg: 'bg-indigo-50', icon: Truck },
    'COMPLETED': { label: 'Đã hoàn thành', color: 'text-emerald-600', bg: 'bg-emerald-50', icon: CheckCircle2 },
    'CANCELLED': { label: 'Đã hủy', color: 'text-rose-600', bg: 'bg-rose-50', icon: XCircle },
  };

  const currentStatus = statusMap[order.status] || statusMap['PENDING'];

  return (
    <div className="bg-[#f8f6f6] dark:bg-[#221610] min-h-screen pt-32 pb-24 font-['Public_Sans']">
      <div className="container mx-auto px-6 max-w-4xl">
        <Link to="/profile" className="inline-flex items-center gap-2 text-xs font-bold text-slate-400 uppercase tracking-widest hover:text-primary transition-all mb-8">
          <ChevronLeft className="size-4" />
          Quay lại hồ sơ
        </Link>

        <div className="bg-white dark:bg-slate-900 rounded-[40px] shadow-xl shadow-slate-200/50 dark:shadow-none border border-slate-100 dark:border-slate-800 overflow-hidden">
          {/* Header */}
          <div className="p-10 border-b border-slate-50 dark:border-slate-800 flex flex-wrap items-center justify-between gap-6 bg-slate-50/50 dark:bg-slate-800/20">
            <div className="space-y-2">
              <h1 className="text-3xl font-black tracking-tight text-[#2d5a27]">Đơn hàng #{order.id}</h1>
              <div className="flex items-center gap-4 text-[10px] font-bold text-slate-400 uppercase tracking-widest">
                <span className="flex items-center gap-1"><Calendar className="size-3" /> {new Date(order.createdAt).toLocaleString('vi-VN')}</span>
                <span className="size-1 rounded-full bg-slate-300"></span>
                <span>{order.orderDetails?.length || 0} sản phẩm</span>
              </div>
            </div>
            <div className={`flex items-center gap-2 px-6 py-3 rounded-2xl font-black text-xs uppercase tracking-[0.2em] border ${currentStatus.bg} ${currentStatus.color} border-current/20`}>
              <currentStatus.icon className="size-4" />
              {currentStatus.label}
            </div>
          </div>

          <div className="p-10">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-12 mb-12">
              {/* Shipping Info */}
              <div className="space-y-6">
                <h3 className="text-lg font-bold flex items-center gap-2 text-[#2d5a27]">
                  <MapPin className="size-5" />
                  Thông tin nhận hàng
                </h3>
                <div className="p-6 rounded-3xl bg-[#f8f6f6] dark:bg-slate-950 space-y-4">
                  <div className="flex items-start gap-4">
                     <User className="size-4 text-slate-400 mt-1" />
                     <div className="space-y-1">
                        <p className="text-xs font-bold text-slate-500 uppercase tracking-widest leading-none">Người nhận</p>
                        <p className="font-bold text-sm">{order.fullName}</p>
                     </div>
                  </div>
                  <div className="flex items-start gap-4">
                     <Phone className="size-4 text-slate-400 mt-1" />
                     <div className="space-y-1">
                        <p className="text-xs font-bold text-slate-500 uppercase tracking-widest leading-none">Điện thoại</p>
                        <p className="font-bold text-sm">{order.phone}</p>
                     </div>
                  </div>
                  <div className="flex items-start gap-4">
                     <MapPin className="size-4 text-slate-400 mt-1" />
                     <div className="space-y-1">
                        <p className="text-xs font-bold text-slate-500 uppercase tracking-widest leading-none">Địa chỉ</p>
                        <p className="font-bold text-sm leading-relaxed">{order.address}</p>
                     </div>
                  </div>
                </div>
              </div>

              {/* Payment Info */}
              <div className="space-y-6">
                <h3 className="text-lg font-bold flex items-center gap-2 text-[#2d5a27]">
                  <CreditCard className="size-5" />
                  Thanh toán
                </h3>
                <div className="p-6 rounded-3xl bg-[#f8f6f6] dark:bg-slate-950 space-y-4">
                  <div className="flex items-center justify-between">
                    <span className="text-xs font-bold text-slate-500 uppercase tracking-widest">Phương thức</span>
                    <span className="text-sm font-bold">{order.paymentMethod === 'COD' ? 'Tiền mặt khi nhận hàng' : 'Chuyển khoản / Ví điện tử'}</span>
                  </div>
                  <div className="flex items-center justify-between">
                    <span className="text-xs font-bold text-slate-500 uppercase tracking-widest">Trạng thái</span>
                    <span className="text-[10px] font-black uppercase tracking-widest text-emerald-500 italic">Đã xác nhận</span>
                  </div>
                  <div className="pt-4 mt-4 border-t border-white dark:border-slate-800 space-y-2">
                    <div className="flex justify-between text-sm font-bold">
                       <span className="text-slate-400 uppercase tracking-tight">Tạm tính:</span>
                       <span>{(order.totalAmount - (order.shippingFee || 30000)).toLocaleString()}đ</span>
                    </div>
                    <div className="flex justify-between text-sm font-bold">
                       <span className="text-slate-400 uppercase tracking-tight">Vận chuyển:</span>
                       <span className="text-emerald-500">+{(order.shippingFee || 30000).toLocaleString()}đ</span>
                    </div>
                    <div className="flex justify-between text-xl font-black pt-2 text-[#2d5a27]">
                       <span className="uppercase tracking-tighter">Tổng cộng:</span>
                       <span>{order.totalAmount?.toLocaleString()}đ</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            {/* Products List */}
            <div className="space-y-6">
              <h3 className="text-lg font-bold flex items-center gap-2 text-[#2d5a27]">
                <Package className="size-5" />
                Sản phẩm đã đặt
              </h3>
              <div className="border border-slate-50 dark:border-slate-800 rounded-3xl overflow-hidden shadow-inner">
                <table className="w-full text-left">
                  <thead className="bg-[#f8f6f6] dark:bg-slate-950 text-[10px] font-black uppercase tracking-[0.2em] text-slate-500 border-b border-slate-50 dark:border-slate-800">
                    <tr>
                      <th className="px-8 py-6">Sản phẩm</th>
                      <th className="px-8 py-6 text-center">SL</th>
                      <th className="px-8 py-6 text-right">Đơn giá</th>
                      <th className="px-8 py-6 text-right">Thành tiền</th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-50 dark:divide-slate-800">
                    {order.orderDetails?.map((item, idx) => (
                      <tr key={idx} className="hover:bg-slate-50/50 dark:hover:bg-slate-800/30 transition-colors">
                        <td className="px-8 py-6">
                          <div className="flex items-center gap-4">
                            <div className="size-14 rounded-xl bg-[#f8f6f6] dark:bg-slate-950 overflow-hidden shrink-0">
                               <img src={item.product?.imageUrl} alt="" className="size-full object-cover" />
                            </div>
                            <div className="space-y-1">
                               <p className="text-xs font-bold text-slate-900 dark:text-white leading-tight">{item.product?.name}</p>
                               <p className="text-[10px] font-bold text-slate-400 uppercase tracking-widest">{item.weight}g</p>
                            </div>
                          </div>
                        </td>
                        <td className="px-8 py-6 text-center font-bold text-sm">x{item.quantity}</td>
                        <td className="px-8 py-6 text-right font-bold text-sm">{item.price?.toLocaleString()}đ</td>
                        <td className="px-8 py-6 text-right font-black text-sm text-primary">{(item.price * item.quantity).toLocaleString()}đ</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderDetail;
