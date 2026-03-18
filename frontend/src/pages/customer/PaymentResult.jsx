import React, { useEffect, useState } from 'react';
import { useSearchParams, Link } from 'react-router-dom';
import { CheckCircle, XCircle, ChevronRight, Home, ShoppingBag } from 'lucide-react';

const PaymentResult = () => {
    const [searchParams] = useSearchParams();
    const [status, setStatus] = useState('loading');
    
    const responseCode = searchParams.get('vnp_ResponseCode');
    const amount = searchParams.get('vnp_Amount');
    const orderInfo = searchParams.get('vnp_OrderInfo');

    useEffect(() => {
        const verifyPayment = async () => {
            try {
                const response = await fetch(`/api/payment/vnpay-return${window.location.search}`);
                const data = await response.json();
                
                if (data.status === 'success') {
                    setStatus('success');
                } else {
                    setStatus('failed');
                }
            } catch (error) {
                console.error('Lỗi xác thực thanh toán:', error);
                setStatus('failed');
            }
        };

        if (responseCode) {
            verifyPayment();
        }
    }, [responseCode]);

    return (
        <div className="pt-32 pb-24 bg-[#f8f6f6] dark:bg-[#221610] min-h-screen font-['Public_Sans']">
            <div className="container mx-auto px-6 max-w-2xl">
                <div className="bg-white dark:bg-slate-900 rounded-[2.5rem] shadow-2xl overflow-hidden border border-slate-100 dark:border-slate-800 p-12 text-center space-y-8">
                    
                    {status === 'success' ? (
                        <>
                            <div className="flex justify-center">
                                <div className="p-6 bg-emerald-50 dark:bg-emerald-500/10 rounded-full animate-bounce">
                                    <CheckCircle className="size-20 text-emerald-500" />
                                </div>
                            </div>
                            <div className="space-y-3">
                                <h1 className="text-3xl font-black tracking-tight text-[#2d5a27]">Thanh toán thành công!</h1>
                                <p className="text-slate-500 font-medium">Đơn hàng của bạn đã được ghi nhận và đang được xử lý.</p>
                            </div>
                        </>
                    ) : (
                        <>
                            <div className="flex justify-center">
                                <div className="p-6 bg-rose-50 dark:bg-rose-500/10 rounded-full animate-pulse">
                                    <XCircle className="size-20 text-rose-500" />
                                </div>
                            </div>
                            <div className="space-y-3">
                                <h1 className="text-3xl font-black tracking-tight text-rose-600">Thanh toán thất bại</h1>
                                <p className="text-slate-500 font-medium">Có lỗi xảy ra trong quá trình thanh toán. Vui lòng thử lại.</p>
                            </div>
                        </>
                    )}

                    <div className="bg-slate-50 dark:bg-slate-800/50 rounded-3xl p-6 text-left space-y-4">
                        <div className="flex justify-between text-sm">
                            <span className="text-slate-400 font-bold uppercase tracking-widest text-[10px]">Đơn hàng</span>
                            <span className="font-bold">{orderInfo}</span>
                        </div>
                        <div className="flex justify-between text-sm">
                            <span className="text-slate-400 font-bold uppercase tracking-widest text-[10px]">Số tiền</span>
                            <span className="font-bold text-[#2d5a27]">{amount ? (parseInt(amount) / 100).toLocaleString() : 0}đ</span>
                        </div>
                    </div>

                    <div className="flex flex-col sm:flex-row gap-4 pt-4">
                        <Link 
                            to="/" 
                            className="flex-1 bg-white dark:bg-slate-900 border-2 border-slate-100 dark:border-slate-800 hover:border-[#2d5a27] py-4 rounded-2xl font-bold flex items-center justify-center gap-2 transition-all hover:shadow-lg"
                        >
                            <Home className="size-4" />
                            VỀ TRANG CHỦ
                        </Link>
                        <Link 
                            to="/products" 
                            className="flex-1 bg-[#2d5a27] hover:bg-[#1e3d1a] text-white py-4 rounded-2xl font-bold flex items-center justify-center gap-2 transition-all hover:shadow-lg shadow-[#2d5a27]/20"
                        >
                            TIẾP TỤC MUA SẮM
                            <ShoppingBag className="size-4" />
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default PaymentResult;
