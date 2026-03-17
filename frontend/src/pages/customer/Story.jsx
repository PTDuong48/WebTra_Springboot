import React from 'react';
import { Link } from 'react-router-dom';

const Story = () => {
  return (
    <div className="font-display overflow-x-hidden min-h-screen">
      {/* Hero Section */}
      <section className="relative w-full h-[85vh] flex items-center justify-center overflow-hidden pt-20">
        <div className="absolute inset-0 z-0">
          <div className="absolute inset-0 bg-gradient-to-b from-black/30 via-transparent to-slate-50 dark:to-slate-950 z-10"></div>
          <img 
            alt="Tea plantation" 
            className="w-full h-full object-cover" 
            src="https://lh3.googleusercontent.com/aida-public/AB6AXuAHBCrec0ZVHczC6GuzUsHOTl0TqiMdIsoKCV7PUauJ6kEkAuDiMV12cHltnHDfOgPO1mgTP40PeR9XUNhSBXy_xArym0ZbH_U7hmLNfVeWp7rYusxSchiYDwYFBQFQ4bbfMBuNG5oMpmC9CnT5VAGjv2B0rfpcYhlcYhRDFKlJfKKKixiGGS2m05RqwUSUBa_5gSHZZFYRWP2FKdYEDCpnb5ubjRhQUzaO-rkxbZYGTvpznVreLeZlyvXq70WtE-xDvf3hlHJtdPM"
          />
        </div>
        <div className="relative z-20 text-center px-4 max-w-4xl mx-auto mt-16">
          <span className="inline-block px-4 py-1 mb-6 rounded-full bg-[#e8f3ee]/80 dark:bg-primary/20 text-primary text-xs font-bold tracking-widest uppercase backdrop-blur-sm">
            Tinh hoa thảo mộc
          </span>
          <h1 className="text-5xl md:text-7xl font-black text-white mb-6 drop-shadow-lg">
            Câu chuyện Trà Thơm
          </h1>
          <p className="text-lg md:text-xl text-white/90 font-medium max-w-2xl mx-auto mb-10 leading-relaxed">
            Từ những lá trà Việt Nam xanh mướt đến từng tách hương thơm tự nhiên trọn vẹn.
          </p>
          <div className="flex flex-col sm:flex-row items-center justify-center gap-4">
            <Link to="/products" className="bg-primary hover:bg-primary/90 text-white px-10 py-4 rounded-full font-bold transition-all shadow-xl shadow-primary/20">
              Khám phá sản phẩm
            </Link>
          </div>
        </div>
        {/* Decorative Floating Leaf */}
        <div className="absolute bottom-10 right-10 md:right-20 animate-bounce opacity-40 hidden md:block z-20">
          <span className="material-symbols-outlined text-6xl text-[#9db4a0]">eco</span>
        </div>
      </section>

      {/* Nguồn Gốc Section */}
      <section className="py-24 px-6 lg:px-20 bg-[#e8f3ee]/30 dark:bg-slate-900/50">
        <div className="max-w-7xl mx-auto grid grid-cols-1 lg:grid-cols-2 gap-16 items-center">
          <div className="relative">
            <div className="absolute -top-10 -left-10 w-40 h-40 bg-[#9db4a0]/10 rounded-full -z-10"></div>
            <img 
              alt="Tea farmer" 
              className="rounded-3xl shadow-2xl w-full aspect-[4/5] object-cover" 
              src="https://lh3.googleusercontent.com/aida-public/AB6AXuDaceip3YDq5j2P9SBwIWzfds253nhBYaBxNJYnuyhwKW8S0cI0HD8GjUm5F4U6zzOyqUZFDrRqljGmacXrekgUV_4C00LYCArU6HjXt1gf-Nm_t-jIomwQEZMtR05v3YEvcWrtemj6Gb1E-444OEXwoDq6VymZ6NALg5lJhTw-11clfkv0bNsMR-VELth0bCxgmOh7paXYueOeVLAs8hjJBcljgccyNRPS5WRSi7_4BkI3q7D-DszP3OuSFriZUmbvxNf669VwUf4"
            />
            <div className="absolute -bottom-6 -right-6 bg-white dark:bg-slate-900 p-6 rounded-2xl shadow-xl max-w-xs">
              <p className="text-primary font-bold text-2xl mb-1">100%</p>
              <p className="text-slate-500 text-sm leading-snug">Canh tác hữu cơ bền vững tại các vùng trà đặc sản.</p>
            </div>
          </div>
          <div className="flex flex-col gap-6">
            <span className="text-primary font-bold tracking-widest uppercase text-sm">Nguồn gốc tận tâm</span>
            <h2 className="text-4xl md:text-5xl font-black leading-tight text-slate-800 dark:text-slate-100">
              Những búp trà từ <br/><span className="text-[#9db4a0]">cao nguyên sương mù</span>
            </h2>
            <p className="text-slate-600 dark:text-slate-400 text-lg leading-relaxed">
              Trà Thơm khởi nguồn từ niềm đam mê với những đồi chè xanh mướt tại Thái Nguyên, Lâm Đồng và Hà Giang. Chúng tôi tin rằng, một tách trà ngon phải bắt đầu từ lòng đất mẹ và đôi bàn tay cần mẫn của người nông dân.
            </p>
            <p className="text-slate-600 dark:text-slate-400 text-lg leading-relaxed">
              Mỗi sản phẩm là kết quả của quy trình canh tác bền vững, không hóa chất, tôn trọng nhịp điệu tự nhiên để giữ trọn vẹn dược tính và hương vị tinh khiết nhất.
            </p>
            <div className="mt-4 flex items-center gap-6">
              <div className="flex -space-x-3">
                <img alt="Avatar" className="w-12 h-12 rounded-full border-4 border-white dark:border-slate-900 object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDiSR1tf3jdsu9bruaLAoTb71To2zc4Rp67mK4GOnsVCvGpOvle_KBoOqiLOMoi3BM4nuPH-OhRmGOp_LIGQdQNFW8tXX0o4pJ79Rs-UImgCFt-zVxDuq2DpV4rQFTcmL8A505GhH-f-NR3Zvz19ZUHwEjpAUKcBCl0BU9wPr6SyXf-qGe9mGWs-lCCf8ofKShJIq8ThcpHoKIzJmtGSarJfkGzNyq3k9Up_thQsGMMa7pLNpBI6ZSi9f-IjhKbZB5XMWdSQ_FzGNM"/>
                <img alt="Avatar" className="w-12 h-12 rounded-full border-4 border-white dark:border-slate-900 object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAwzkQ0pNUBHlOyRA7ZYqPrnzdA4JQQGRn_E1-7AHcH6YzpEDvmOvqyS3kdwokMAIZm1rSUyJtucipchjAezUM5T4b_LAs-moUa8zPJDyBuQDwaayD7xCHtz6mVsIdLMIQ5rPSUrjzV9z7w16uFXCEqmaXJr2FLr1M2VhlAcJOH8J7wybhIOj_4LvymOCOcv3pG9MPC8l-ALLdsVlJok31iHsOoFqpbcrxkSnDzpcIbBFuc38hNyMJZBHZvkNM2L9i462VBek8PDkI"/>
                <img alt="Avatar" className="w-12 h-12 rounded-full border-4 border-white dark:border-slate-900 object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDArwppAH0X2bXON6s3EhQ_atWcOr6nmUj3eFiLLmJ_D-7anoLGv0nB4i_Ca1Dul3WETlSJin1I7g0pOHUIVodRwWOCIxSfdaZC9s1tmVcdpzaWRpB0cLoH6Yk8emc6UtK6tKtFAGgS5VMxmqVzU4UX1ZOnU9VCTUYI6xpnHRPNynA6OkF45FEfHSv0JfzQWi0iG0c13O3LA_G0rKaA0I-efTY_k_9UPCsh7GBV-NZO9YX_QwOqrleXHwTmdG35WtrRS0lhCMrKrbA"/>
              </div>
              <p className="text-sm font-semibold text-slate-500 italic">Được tin dùng bởi hàng ngàn người yêu trà</p>
            </div>
          </div>
        </div>
      </section>

      {/* Hành Trình Section */}
      <section className="py-24 px-6 lg:px-20 bg-slate-50 dark:bg-slate-950">
        <div className="max-w-7xl mx-auto text-center mb-16">
          <h2 className="text-4xl font-black mb-4 group inline-block">Hành trình từ Búp đến Tách</h2>
          <div className="h-1 w-24 bg-primary mx-auto rounded-full"></div>
        </div>
        <div className="max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-3 gap-8">
          <div className="group">
            <div className="relative overflow-hidden rounded-2xl mb-6">
              <img alt="Hand picking tea" className="w-full aspect-square object-cover transition-transform duration-700 group-hover:scale-110" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBjVOP1_mjf-n87CjfFdwxlWVnzILQvWdJPKbRvznhkdlECKHKIWbNF14JX8gy7qqTkzzOm5p2YLPNEfLY_s-bk6mTslNobS-hjdq_RAvGk52qR5msFHAKmezAlwdRWejlJbCS1n6mioEjeBI6DiYjKT3c7lvEgjJUhE59yRtEME4cl5fdCJa8XGr0NVCSuqO_KMlp2R_ZNZDO6uEaIpoh0FtM1zls6zST4_SjWLVuxlO8VbSI_K2BXoLpZ9-6J5yspYZUPiPW9xpo"/>
              <div className="absolute inset-0 bg-primary/10 opacity-0 group-hover:opacity-100 transition-opacity"></div>
            </div>
            <h3 className="text-xl font-bold mb-2">Thu hái thủ công</h3>
            <p className="text-slate-600 dark:text-slate-400">Từng búp trà "một tôm hai lá" được chọn lọc kỹ lưỡng bởi các nghệ nhân vào lúc sáng sớm tinh sương.</p>
          </div>
          <div className="group">
            <div className="relative overflow-hidden rounded-2xl mb-6">
              <img alt="Tea processing" className="w-full aspect-square object-cover transition-transform duration-700 group-hover:scale-110" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBqZE73wIyM3I6B6BoyzqWyr70TVONqs2kPW7srQ9dDfBOjg8uN-zHUXBmZnLCG6ZDfA30JFFe7DKlei2PPFkwjntKt4my0kr53ijqtWF2mowxLj6-4tP8j3RzuWH22Fc9pzKUAEu5FzRf-jyAQw21JitBBC_Z0Nyire7YkF_AuuZZZAaXW92j2uvlrGjYeJSvC4Mqp7sgJCAwloJ9dxVH4OpaxXYvlKCrDQcqoXb8Ob9i5xJEpP-L9c3JkgawU0UKlnqgzbd-eHc8"/>
              <div className="absolute inset-0 bg-primary/10 opacity-0 group-hover:opacity-100 transition-opacity"></div>
            </div>
            <h3 className="text-xl font-bold mb-2">Sao trà nghệ nhân</h3>
            <p className="text-slate-600 dark:text-slate-400">Quy trình sao tay truyền thống kết hợp công nghệ kiểm soát nhiệt hiện đại để giữ hương thơm nồng nàn.</p>
          </div>
          <div className="group">
            <div className="relative overflow-hidden rounded-2xl mb-6">
              <img alt="Tea cupping" className="w-full aspect-square object-cover transition-transform duration-700 group-hover:scale-110" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBrl03G0NfaHnm2RH_ohhwGD5_ADyDT2Rup6M2fpN39wO75ekkTuJZdCRVdUbjbyT10Ddd0pAWHo0ph4WaL4ruhTaOFaNQHwNLt1PkUesCbrRby5hVt5BK4KUQJfFp01lu-qB33fpJp98MTfCWYIxFS8hfr_18FE8mRHMf_zw28iaQFD33f1-JiQl7RUvZQ1H767O-Do1OGV-KgwgcodZWLUh7Z5A-IrLp81i2qmpq2TZeIrHAxAy891ZRvk56cTL0tTyjBx7hhMNs"/>
              <div className="absolute inset-0 bg-primary/10 opacity-0 group-hover:opacity-100 transition-opacity"></div>
            </div>
            <h3 className="text-xl font-bold mb-2">Đóng gói tinh hoa</h3>
            <p className="text-slate-600 dark:text-slate-400">Sản phẩm được đóng gói trong môi trường vô trùng, bảo quản tốt nhất các dưỡng chất tự nhiên.</p>
          </div>
        </div>
      </section>

      {/* Cam Kết Section */}
      <section className="py-20 px-6 lg:px-20 bg-[#9db4a0]/10 dark:bg-primary/5">
        <div className="max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-3 gap-12">
          <div className="flex flex-col items-center text-center p-8 bg-white dark:bg-slate-900 rounded-3xl shadow-sm border border-[#9db4a0]/10 hover:shadow-xl hover:-translate-y-1 transition-all">
            <div className="w-16 h-16 bg-primary/10 rounded-full flex items-center justify-center text-primary mb-6">
              <span className="material-symbols-outlined text-4xl">energy_savings_leaf</span>
            </div>
            <h4 className="text-xl font-bold mb-3">100% Tự nhiên</h4>
            <p className="text-slate-500 text-sm">Không hương liệu nhân tạo, không phẩm màu. Vị trà thật, hương trà thật.</p>
          </div>
          <div className="flex flex-col items-center text-center p-8 bg-white dark:bg-slate-900 rounded-3xl shadow-sm border border-[#9db4a0]/10 hover:shadow-xl hover:-translate-y-1 transition-all">
            <div className="w-16 h-16 bg-primary/10 rounded-full flex items-center justify-center text-primary mb-6">
              <span className="material-symbols-outlined text-4xl">verified_user</span>
            </div>
            <h4 className="text-xl font-bold mb-3">Sạch &amp; An toàn</h4>
            <p className="text-slate-500 text-sm">Đạt chuẩn VietGAP và các chứng nhận quốc tế về an toàn thực phẩm.</p>
          </div>
          <div className="flex flex-col items-center text-center p-8 bg-white dark:bg-slate-900 rounded-3xl shadow-sm border border-[#9db4a0]/10 hover:shadow-xl hover:-translate-y-1 transition-all">
            <div className="w-16 h-16 bg-primary/10 rounded-full flex items-center justify-center text-primary mb-6">
              <span className="material-symbols-outlined text-4xl">volunteer_activism</span>
            </div>
            <h4 className="text-xl font-bold mb-3">Hỗ trợ nông dân</h4>
            <p className="text-slate-500 text-sm">Chúng tôi trích 5% lợi nhuận để hỗ trợ cộng đồng nông dân địa phương.</p>
          </div>
        </div>
      </section>

      {/* Lifestyle Section */}
      <section className="py-24 px-6 lg:px-20">
        <div className="max-w-7xl mx-auto flex flex-col lg:flex-row items-center gap-20">
          <div className="w-full lg:w-1/2 order-2 lg:order-1">
            <div className="grid grid-cols-2 gap-4">
              <img alt="Family tea time" className="rounded-2xl h-64 w-full object-cover shadow-lg" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAYoV9MrSjr0lb9QnZNMAPiQye1E76_u-0E54ycSVKN-h5jdRYlY-LVyjkeBGigx_igAouXpwqhbntrPYns-qd2mcaPvSLH0a8mcYe_-8I5KCfkb4kWrZWaOlozmQ7g-pfyTw7NpVib-yUdZSYfdE13S3YNkzdR-r8vgbJBcifZB78543it_BGnaY_pKN8mCo2LcH58rMp46AQlJIeThuDKbscb3vMjEtnPWdR6hUwfHNy5USSu6lmoCNnhThvlF1tZ6NlskI93ZK8"/>
              <img alt="Tea cup lifestyle" className="rounded-2xl h-64 w-full object-cover mt-8 shadow-lg" src="https://lh3.googleusercontent.com/aida-public/AB6AXuD968wtK4UPaufERkVDWkCqdvqkn7QB-24YeWx50KteEpWzjahtMX_9s257TjTAcQsUEFOx9ke3iB2R8lz0kQGBo7qi3U4doouFqBUNnEHkxlCB8cESVjyhm3dEfCPEU9eouEmux5POQEoUTkcIS8kTQv_l6lWe5bsbbisfoBQSADYvZoUUOCrti46K2xlvbJ87gTAlknFaEsP3MvHUMCoSd8KpzccLSeVuw_M4_r0eJE016OKJPHtaa6Je9nU0gcGCmfqNyK8xrh8"/>
            </div>
          </div>
          <div className="w-full lg:w-1/2 order-1 lg:order-2">
            <h2 className="text-4xl font-black mb-6">Hơn cả một tách trà, đó là <br/><span className="text-primary italic">sự kết nối</span></h2>
            <p className="text-slate-600 dark:text-slate-400 text-lg mb-8">
              Trong nhịp sống hối hả, Trà Thơm là một nốt lặng cần thiết. Là phút giây quây quần bên gia đình, là cuộc trò chuyện thân tình cùng tri kỷ, hay đơn giản là phút thư thái đối diện với chính mình.
            </p>
            <div className="flex flex-col gap-4">
              <div className="flex items-start gap-4">
                <span className="material-symbols-outlined text-primary">check_circle</span>
                <p className="font-medium text-slate-700 dark:text-slate-300">Thanh lọc tâm hồn qua hương vị thanh khiết</p>
              </div>
              <div className="flex items-start gap-4">
                <span className="material-symbols-outlined text-primary">check_circle</span>
                <p className="font-medium text-slate-700 dark:text-slate-300">Món quà sức khỏe cho người thân yêu</p>
              </div>
              <div className="flex items-start gap-4">
                <span className="material-symbols-outlined text-primary">check_circle</span>
                <p className="font-medium text-slate-700 dark:text-slate-300">Gìn giữ nét đẹp văn hóa thưởng trà Việt</p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Founder Quote Card */}
      <section className="py-20 px-6 lg:px-20">
        <div className="max-w-4xl mx-auto bg-[#7c6a5a]/5 dark:bg-[#7c6a5a]/10 p-12 md:p-20 rounded-[3rem] text-center relative border border-[#7c6a5a]/10">
          <span className="material-symbols-outlined text-6xl text-[#7c6a5a]/20 absolute top-10 left-10">format_quote</span>
          <p className="text-2xl md:text-3xl font-display italic text-[#7c6a5a] dark:text-[#7c6a5a]/80 leading-relaxed mb-10">
            "Tôi không chỉ bán trà, tôi muốn mang cả hương rừng, gió núi và tình người của vùng đất Việt Nam gói trọn vào trong từng tách trà gửi đến bạn."
          </p>
          <div className="flex flex-col items-center">
            <img alt="Founder" className="w-20 h-20 rounded-full mb-4 object-cover grayscale" src="https://lh3.googleusercontent.com/aida-public/AB6AXuD2ZnCA5EbRBwe8m1MbPu84jsiFUjE-BbkSJnxIWcB67aJouGXKSxks2RkhV-B4QSjlYOMKlD5EIe7kGttGDC_dSrE2xSoIJS4xDKGqBASSylf81Ugd_AwXqy7o8R-_Jx41BoupWagsXAYhueyQ27ACLWqhok42IauMsyLhxXOJPLLkKkXi81CFyjJ0ZOr_Zxvg0bB7ZBT6HrCFP9Rp6RUJ-iYA1HWAGMKM8oMXQl5XRU88b7aeHyzAGtNe1MndJcGD_TG9A5_7UuM"/>
            <h5 className="text-xl font-black text-slate-800 dark:text-slate-200">Nguyễn Văn Thơm</h5>
            <p className="text-slate-500 uppercase tracking-widest text-xs font-bold mt-1">Founder &amp; Tea Master</p>
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-24 px-6 lg:px-20 text-center">
        <div className="max-w-3xl mx-auto">
          <h2 className="text-4xl md:text-5xl font-black mb-8">Bắt đầu hành trình vị giác của bạn</h2>
          <p className="text-slate-600 dark:text-slate-400 mb-10 text-lg">Hàng ngàn người đã tìm thấy sự an nhiên từ Trà Thơm. Còn bạn thì sao?</p>
          <div className="flex flex-wrap justify-center gap-4">
            <Link to="/products" className="bg-primary text-white px-12 py-5 rounded-full font-black text-lg shadow-xl shadow-primary/30 hover:scale-105 transition-transform inline-block">
              Khám phá sản phẩm ngay
            </Link>
          </div>
        </div>
      </section>
      
      {/* Decorative floating leaves (Abstract pattern) */}
      <div className="fixed pointer-events-none opacity-10 dark:opacity-5 inset-0 overflow-hidden -z-10">
        <span className="material-symbols-outlined absolute text-primary text-4xl top-[15%] left-[5%] rotate-45">eco</span>
        <span className="material-symbols-outlined absolute text-primary text-2xl top-[45%] right-[8%] -rotate-12">eco</span>
        <span className="material-symbols-outlined absolute text-primary text-5xl bottom-[20%] left-[10%] rotate-[120deg]">eco</span>
        <span className="material-symbols-outlined absolute text-primary text-3xl bottom-[10%] right-[15%] -rotate-45">eco</span>
      </div>
    </div>
  );
};

export default Story;
