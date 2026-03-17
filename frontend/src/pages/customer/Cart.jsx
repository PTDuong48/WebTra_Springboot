import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { 
  Trash2, 
  Plus, 
  Minus, 
  ShoppingBag, 
  ArrowRight,
  ChevronLeft,
  Truck,
  ShieldCheck
} from 'lucide-react';

import { useCart } from '../../context/CartContext';

const Cart = () => {
  const { cartItems, updateQuantity, removeItem, subtotal } = useCart();

  const total = subtotal;

  return (
    <div className="pt-32 pb-24">
      <div className="container mx-auto px-6">
        <h1 className="text-3xl font-bold tracking-tighter mb-12">Giỏ hàng <span className="text-primary italic">của bạn</span></h1>

        {cartItems.length > 0 ? (
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-12">
            {/* Items List */}
            <div className="lg:col-span-2 space-y-6">
              <div className="bg-white dark:bg-slate-900 rounded-[32px] border border-slate-100 dark:border-slate-800 shadow-sm overflow-hidden">
                <table className="w-full text-left">
                  <thead>
                    <tr className="border-b border-slate-50 dark:border-slate-800">
                      <th className="px-6 py-4 text-[10px] font-black uppercase tracking-widest text-slate-400">Sản phẩm</th>
                      <th className="px-6 py-4 text-[10px] font-black uppercase tracking-widest text-slate-400 hidden sm:table-cell text-center">Số lượng</th>
                      <th className="px-6 py-4 text-[10px] font-black uppercase tracking-widest text-slate-400 text-right">Tổng cộng</th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-50 dark:divide-slate-800">
                    {cartItems.map((item) => (
                      <tr key={item.itemKey} className="group transition-colors hover:bg-slate-50/50 dark:hover:bg-slate-800/50">
                        <td className="px-6 py-6">
                          <div className="flex items-center gap-6">
                            <div className="size-20 rounded-2xl bg-slate-50 dark:bg-slate-800 border border-slate-100 dark:border-slate-800 overflow-hidden shrink-0">
                               <img src={item.image} alt={item.name} className="size-full object-cover" />
                            </div>
                            <div className="space-y-1">
                              <h4 className="font-black text-slate-900 dark:text-white leading-tight">{item.name}</h4>
                              <p className="text-xs font-bold text-slate-400 uppercase tracking-widest">{item.weight}g</p>
                              <div className="sm:hidden flex items-center gap-4 mt-2">
                                <button onClick={() => updateQuantity(item.itemKey, -1)} className="p-1 rounded bg-slate-100 dark:bg-slate-800 dark:text-white"><Minus className="size-3" /></button>
                                <span className="text-sm font-bold dark:text-white">{item.quantity}</span>
                                <button onClick={() => updateQuantity(item.itemKey, 1)} className="p-1 rounded bg-slate-100 dark:bg-slate-800 dark:text-white"><Plus className="size-3" /></button>
                              </div>
                            </div>
                          </div>
                        </td>
                        <td className="px-6 py-6 hidden sm:table-cell">
                          <div className="flex items-center justify-center gap-4">
                            <div className="flex items-center p-1 rounded-xl bg-slate-50 dark:bg-slate-800 border border-slate-100 dark:border-slate-700">
                              <button onClick={() => updateQuantity(item.itemKey, -1)} className="size-8 flex items-center justify-center hover:bg-white dark:hover:bg-slate-700 rounded-lg transition-all dark:text-white"><Minus className="size-3" /></button>
                              <span className="w-8 text-center font-bold dark:text-white">{item.quantity}</span>
                              <button onClick={() => updateQuantity(item.itemKey, 1)} className="size-8 flex items-center justify-center hover:bg-white dark:hover:bg-slate-700 rounded-lg transition-all dark:text-white"><Plus className="size-3" /></button>
                            </div>
                            <button onClick={() => removeItem(item.itemKey)} className="p-2 text-slate-300 hover:text-rose-500 transition-colors">
                              <Trash2 className="size-5" />
                            </button>
                          </div>
                        </td>
                        <td className="px-6 py-6 text-right font-black text-primary">
                          {(item.price * item.quantity).toLocaleString()}đ
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
              
              <Link to="/products" className="inline-flex items-center gap-2 text-sm font-bold text-slate-400 hover:text-primary transition-all group">
                <ChevronLeft className="size-4 group-hover:-translate-x-1 transition-transform" />
                Tiếp tục mua hàng
              </Link>
            </div>

            {/* Order Summary */}
            <div className="space-y-6">
              <div className="card space-y-6">
                <h3 className="text-xl font-black tracking-tight">Tóm tắt đơn hàng</h3>
                
                <div className="space-y-4">
                  <div className="flex justify-between text-lg font-bold text-slate-500">
                    <span>Tạm tính</span>
                    <span className="text-slate-900 dark:text-white">{subtotal.toLocaleString()}đ</span>
                  </div>
                  
                  <div className="h-px bg-slate-100 dark:bg-slate-800"></div>
                  <div className="flex justify-between text-2xl font-bold tracking-tight">
                    <span>Tổng cộng</span>
                    <span className="text-primary font-black">{total.toLocaleString()}đ</span>
                  </div>
                </div>

                <Link to="/checkout" className="btn-primary w-full h-[56px] text-base justify-between">
                  Thanh toán ngay
                  <ArrowRight className="size-6" />
                </Link>

                <div className="flex items-center justify-center gap-2 text-[10px] font-bold text-slate-400 uppercase tracking-widest pt-2">
                   <ShieldCheck className="size-4 text-emerald-500" />
                   Thanh toán an toàn & mã hóa 100%
                </div>
              </div>
            </div>
          </div>
        ) : (
          <div className="flex flex-col items-center justify-center py-32 space-y-8">
            <div className="size-32 rounded-[40px] bg-slate-50 dark:bg-slate-900 flex items-center justify-center text-slate-200">
              <ShoppingBag className="size-16" />
            </div>
            <div className="text-center space-y-4">
              <h2 className="text-3xl font-black tracking-tight">Giỏ hàng đang trống</h2>
              <p className="text-slate-500 font-medium">Bạn chưa chọn được loại trà nào ưng ý sao? Hãy khám phá ngay nhé!</p>
            </div>
            <Link to="/products" className="btn-primary px-10 h-14">
              Khám phám ngay thôi
              <ArrowRight className="size-5" />
            </Link>
          </div>
        )}
      </div>
    </div>
  );
};

export default Cart;
