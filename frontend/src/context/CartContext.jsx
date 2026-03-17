import React, { createContext, useContext, useState, useEffect } from 'react';

const CartContext = createContext();

export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState(() => {
    const savedCart = localStorage.getItem('cart');
    return savedCart ? JSON.parse(savedCart) : [];
  });

  useEffect(() => {
    localStorage.setItem('cart', JSON.stringify(cartItems));
  }, [cartItems]);

  const addToCart = (product, weight, quantity) => {
    setCartItems(prev => {
      const itemKey = `${product.id}-${weight.id}`;
      const existingItem = prev.find(item => item.itemKey === itemKey);

      if (existingItem) {
        return prev.map(item => 
          item.itemKey === itemKey 
            ? { ...item, quantity: item.quantity + quantity }
            : item
        );
      }

      return [...prev, {
        itemKey,
        id: product.id,
        name: product.name,
        image: product.imageUrl,
        weightId: weight.id,
        weight: weight.weight,
        price: weight.price,
        stock: weight.stock,
        quantity: quantity
      }];
    });
  };

  const updateQuantity = (itemKey, delta) => {
    setCartItems(prev => prev.map(item => {
      if (item.itemKey === itemKey) {
        const newQty = Math.max(1, item.quantity + delta);
        // Optional: check stock here
        if (delta > 0 && newQty > item.stock) {
          alert(`Chỉ còn ${item.stock} túi trong kho!`);
          return item;
        }
        return { ...item, quantity: newQty };
      }
      return item;
    }));
  };

  const removeItem = (itemKey) => {
    setCartItems(prev => prev.filter(item => item.itemKey !== itemKey));
  };

  const clearCart = () => {
    setCartItems([]);
  };

  const cartCount = cartItems.reduce((acc, item) => acc + item.quantity, 0);
  const subtotal = cartItems.reduce((acc, item) => acc + (item.price * item.quantity), 0);

  return (
    <CartContext.Provider value={{ 
      cartItems, 
      addToCart, 
      updateQuantity, 
      removeItem, 
      clearCart,
      cartCount,
      subtotal
    }}>
      {children}
    </CartContext.Provider>
  );
};
