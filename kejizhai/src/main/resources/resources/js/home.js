// 轮播图实现

document.addEventListener('DOMContentLoaded', () => {
    const slides = document.querySelectorAll('.carousel-item');
    const prevButton = document.querySelector('.carousel-control.prev');
    const nextButton = document.querySelector('.carousel-control.next');
    let currentIndex = 0;
    const slideInterval = 5000; // 自动播放间隔（毫秒）
    let autoPlay;

    function showSlide(index) {
        slides.forEach((slide, i) => {
            slide.classList.toggle('active', i === index);
        });
    }

    function nextSlide() {
        currentIndex = (currentIndex + 1) % slides.length;
        showSlide(currentIndex);
    }

    function prevSlide() {
        currentIndex = (currentIndex - 1 + slides.length) % slides.length;
        showSlide(currentIndex);
    }

    function startAutoPlay() {
        autoPlay = setInterval(nextSlide, slideInterval);
    }

    function stopAutoPlay() {
        clearInterval(autoPlay);
    }

    nextButton.addEventListener('click', () => {
        nextSlide();
        stopAutoPlay();
        startAutoPlay();
    });

    prevButton.addEventListener('click', () => {
        prevSlide();
        stopAutoPlay();
        startAutoPlay();
    });

    showSlide(currentIndex); // 显示第一个幻灯片
    startAutoPlay(); // 开始自动播放
});


document.addEventListener('DOMContentLoaded', () => {
    // 动态加载商品数据
    const productContainer = document.getElementById('productContainer');

    function fetchProducts() {
        fetch('/api/items') // 替换为后端实际的 API 地址
            .then(response => {
                if (!response.ok) {
                    throw new Error('网络响应失败');
                }
                return response.json();
            })
            .then(products => {
                renderProducts(products);
            })
            .catch(error => {
                console.error('获取商品数据失败:', error);
            });
    }

    function renderProducts(products) {
        productContainer.innerHTML = ''; // 清空容器
        products.forEach(product => {
            const productCard = document.createElement('div');
            productCard.className = 'product-card';
            productCard.innerHTML = `
                <img src="${items.iimage}" alt="${items.iname}">
                <div class="name">${items.iname}</div>
                <div class="price">${items.price}</div>
            `;
            productContainer.appendChild(productCard);
        });
    }

    fetchProducts(); // 调用函数加载商品数据
});