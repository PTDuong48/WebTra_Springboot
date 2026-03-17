import React from 'react';
import { Facebook, Instagram, Twitter, Mail, MapPin, Phone } from 'lucide-react';
import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer className="bg-emerald-950 border-t border-white/5 pt-20 pb-10 relative overflow-hidden">
      {/* Decorative leaf background */}
      <div className="absolute top-0 right-0 size-96 bg-primary/5 rounded-full blur-[100px] -translate-y-1/2 translate-x-1/2"></div>
      
      <div className="container mx-auto px-6 relative z-10">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-12 mb-20">
          {/* Brand */}
          <div className="space-y-6">
            <Link to="/" className="flex items-center gap-2 group">
              <span className="material-symbols-outlined text-4xl text-primary group-hover:rotate-12 transition-transform">spa</span>
              <span className="text-2xl font-black tracking-tight uppercase text-white">
                Trà Thơm
              </span>
            </Link>
            <p className="text-slate-400 text-sm leading-relaxed font-bold">
              Tinh hoa trà Việt, mang đến hương vị thanh khiết và tinh tế cho từng tách trà mỗi ngày. Chúng tôi tự hào đi đầu trong văn hóa thưởng trà hiện đại.
            </p>
            <div className="flex gap-4">
              {[Facebook, Instagram, Twitter].map((Icon, i) => (
                <button key={i} className="size-10 rounded-xl bg-white/5 flex items-center justify-center text-slate-300 hover:bg-primary hover:text-white transition-all shadow-sm">
                  <Icon className="size-5" />
                </button>
              ))}
            </div>
          </div>

          {/* Direct Links */}
          <div>
            <h4 className="text-[10px] font-black uppercase tracking-[0.3em] mb-8 text-primary">Khám phá</h4>
            <ul className="space-y-4">
              {['Trang chủ', 'Sản phẩm', 'Câu chuyện', 'Blog', 'Liên hệ'].map((item) => (
                <li key={item}>
                  <Link to="#" className="text-sm font-black text-slate-300 hover:text-primary transition-colors flex items-center gap-2 group uppercase tracking-widest">
                    <span className="size-1 rounded-full bg-white/20 group-hover:bg-primary transition-all"></span>
                    {item}
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          {/* Policies */}
          <div>
            <h4 className="text-[10px] font-black uppercase tracking-[0.3em] mb-8 text-primary">Quy định</h4>
            <ul className="space-y-4">
              {['Chính sách bảo mật', 'Điều khoản sử dụng', 'Chính sách vận chuyển', 'Chính sách đổi trả'].map((item) => (
                <li key={item}>
                  <Link to="#" className="text-sm font-black text-slate-300 hover:text-primary transition-colors flex items-center gap-2 group uppercase tracking-widest">
                    <span className="size-1 rounded-full bg-white/20 group-hover:bg-primary transition-all"></span>
                    {item}
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          {/* Contact */}
          <div>
            <h4 className="text-[10px] font-black uppercase tracking-[0.3em] mb-8 text-primary">Liên hệ</h4>
            <ul className="space-y-6">
              <li className="flex gap-4">
                <div className="size-10 rounded-xl bg-white/5 flex items-center justify-center text-primary shrink-0">
                  <MapPin className="size-5" />
                </div>
                <div>
                  <p className="text-[8px] font-black text-slate-500 uppercase tracking-[0.2em] mb-1">Địa chỉ</p>
                  <p className="text-sm font-black text-slate-200">123 Đường Trà, Quận 1, TP. HCM</p>
                </div>
              </li>
              <li className="flex gap-4">
                <div className="size-10 rounded-xl bg-white/5 flex items-center justify-center text-primary shrink-0">
                  <Phone className="size-5" />
                </div>
                <div>
                  <p className="text-[8px] font-black text-slate-500 uppercase tracking-[0.2em] mb-1">Điện thoại</p>
                  <p className="text-sm font-black text-slate-200">0123 456 789</p>
                </div>
              </li>
            </ul>
          </div>
        </div>

        <div className="pt-10 border-t border-white/5 flex flex-col md:flex-row justify-between items-center gap-4 text-center">
          <p className="text-[8px] font-black uppercase tracking-[0.3em] text-slate-500">
            © 2024 Trà Thơm Store. Phát triển bởi Advanced Agentic Coding Team.
          </p>
          <div className="flex gap-2 opacity-50">
            <div className="h-6 w-10 bg-white/5 rounded-md"></div>
            <div className="h-6 w-10 bg-white/5 rounded-md"></div>
            <div className="h-6 w-10 bg-white/5 rounded-md"></div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
