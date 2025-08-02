// Simple RSA Project JavaScript - Student Project
document.addEventListener('DOMContentLoaded', function() {
    // Initialize all functionality
    initNavigation();
    initScrollEffects();
    initFormHandling();
    initAnimations();
    initMobileMenu();
    initFuelPricing();
});

// Navigation functionality
function initNavigation() {
    const navbar = document.getElementById('navbar');
    const mobileMenuBtn = document.getElementById('mobile-menu-btn');
    const navLinks = document.getElementById('nav-links');

    // Navbar scroll effect
    window.addEventListener('scroll', function() {
        if (window.scrollY > 100) {
            navbar.classList.add('scrolled');
        } else {
            navbar.classList.remove('scrolled');
        }
    });

    // Mobile menu toggle
    if (mobileMenuBtn) {
        mobileMenuBtn.addEventListener('click', function() {
            navLinks.classList.toggle('active');
            const icon = mobileMenuBtn.querySelector('i');
            if (navLinks.classList.contains('active')) {
                icon.className = 'fas fa-times';
            } else {
                icon.className = 'fas fa-bars';
            }
        });
    }

    // Smooth scrolling for navigation links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                const offsetTop = target.offsetTop - 80; // Account for fixed navbar
                window.scrollTo({
                    top: offsetTop,
                    behavior: 'smooth'
                });
                
                // Close mobile menu if open
                if (navLinks.classList.contains('active')) {
                    navLinks.classList.remove('active');
                    mobileMenuBtn.querySelector('i').className = 'fas fa-bars';
                }
            }
        });
    });
}

// Scroll animations
function initScrollEffects() {
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('visible');
            }
        });
    }, observerOptions);

    // Observe fade-in elements
    document.querySelectorAll('.fade-in').forEach(el => {
        observer.observe(el);
    });
}

// Form handling
function initFormHandling() {
    // Fuel delivery form
    const fuelForm = document.getElementById('fuelForm');
    if (fuelForm) {
        fuelForm.addEventListener('submit', function(e) {
            e.preventDefault();
            handleFuelRequest(this);
        });
    }

    // Breakdown form
    const breakdownForm = document.getElementById('breakdownForm');
    if (breakdownForm) {
        breakdownForm.addEventListener('submit', function(e) {
            e.preventDefault();
            handleBreakdownRequest(this);
        });
    }

    // Contact form
    const contactForm = document.getElementById('contactForm');
    if (contactForm) {
        contactForm.addEventListener('submit', function(e) {
            e.preventDefault();
            handleContactRequest(this);
        });
    }

    // Payment form
    const paymentForm = document.getElementById('paymentForm');
    if (paymentForm) {
        paymentForm.addEventListener('submit', function(e) {
            e.preventDefault();
            handlePayment(this);
        });
    }
}

// Fuel pricing functionality
function initFuelPricing() {
    // Initialize price calculation
    calculateTotal();
}

// Calculate fuel total based on selection
function calculateTotal() {
    const fuelType = document.getElementById('fuelType');
    const fuelAmount = document.getElementById('fuelAmount');
    const fuelCost = document.getElementById('fuelCost');
    const deliveryFee = document.getElementById('deliveryFee');
    const totalAmount = document.getElementById('totalAmount');

    if (!fuelType || !fuelAmount || !fuelCost || !deliveryFee || !totalAmount) {
        return;
    }

    const selectedOption = fuelType.options[fuelType.selectedIndex];
    const pricePerLiter = parseFloat(selectedOption.getAttribute('data-price')) || 0;
    const amount = parseFloat(fuelAmount.value) || 0;

    // Calculate delivery fee based on fuel type
    let deliveryFeeAmount = 0;
    if (fuelType.value === 'cng') {
        deliveryFeeAmount = 75;
    } else {
        deliveryFeeAmount = 50;
    }

    const fuelCostAmount = pricePerLiter * amount;
    const total = fuelCostAmount + deliveryFeeAmount;

    // Update display
    fuelCost.textContent = `₹${fuelCostAmount.toFixed(2)}`;
    deliveryFee.textContent = `₹${deliveryFeeAmount.toFixed(2)}`;
    totalAmount.textContent = `₹${total.toFixed(2)}`;
}

// Animations
function initAnimations() {
    // Simple fade-in animation for service cards
    const serviceCards = document.querySelectorAll('.service-card');
    serviceCards.forEach((card, index) => {
        card.style.animationDelay = `${index * 0.2}s`;
    });
}

// Mobile menu
function initMobileMenu() {
    // Close mobile menu when clicking outside
    document.addEventListener('click', function(e) {
        const mobileMenuBtn = document.getElementById('mobile-menu-btn');
        const navLinks = document.getElementById('nav-links');
        
        if (!mobileMenuBtn.contains(e.target) && !navLinks.contains(e.target)) {
            navLinks.classList.remove('active');
            mobileMenuBtn.querySelector('i').className = 'fas fa-bars';
        }
    });
}

// Modal functions
function openFuelModal() {
    const modal = document.getElementById('fuelModal');
    if (modal) {
        modal.style.display = 'block';
        document.body.style.overflow = 'hidden';
    }
}

function closeFuelModal() {
    const modal = document.getElementById('fuelModal');
    if (modal) {
        modal.style.display = 'none';
        document.body.style.overflow = 'auto';
    }
}

function openBreakdownModal() {
    const modal = document.getElementById('breakdownModal');
    if (modal) {
        modal.style.display = 'block';
        document.body.style.overflow = 'hidden';
    }
}

function closeBreakdownModal() {
    const modal = document.getElementById('breakdownModal');
    if (modal) {
        modal.style.display = 'none';
        document.body.style.overflow = 'auto';
    }
}

function openPaymentModal(orderData) {
    const modal = document.getElementById('paymentModal');
    const orderSummary = document.getElementById('orderSummary');
    
    if (modal && orderSummary) {
        // Display order summary
        orderSummary.innerHTML = `
            <div class="order-details">
                <p><strong>Fuel Type:</strong> ${orderData.fuelType}</p>
                <p><strong>Amount:</strong> ${orderData.amount} liters</p>
                <p><strong>Fuel Cost:</strong> ₹${orderData.fuelCost}</p>
                <p><strong>Delivery Fee:</strong> ₹${orderData.deliveryFee}</p>
                <p><strong>Total:</strong> ₹${orderData.total}</p>
            </div>
        `;
        
        modal.style.display = 'block';
        document.body.style.overflow = 'hidden';
    }
}

function closePaymentModal() {
    const modal = document.getElementById('paymentModal');
    if (modal) {
        modal.style.display = 'none';
        document.body.style.overflow = 'auto';
    }
}

// Calculate delivery time based on fuel type and amount
function calculateDeliveryTime(fuelType, amount) {
    let baseTime = 30; // Base delivery time in minutes
    
    // Adjust time based on fuel type
    if (fuelType === 'cng') {
        baseTime += 15; // CNG takes longer to deliver
    }
    
    // Adjust time based on amount
    if (amount > 50) {
        baseTime += 10; // Large orders take longer
    } else if (amount > 20) {
        baseTime += 5; // Medium orders take slightly longer
    }
    
    return baseTime;
}

// Generate order ID
function generateOrderId() {
    const timestamp = Date.now();
    const random = Math.floor(Math.random() * 1000);
    return `RSA${timestamp}${random}`;
}

// Show payment confirmation modal
function showPaymentConfirmation(orderData, orderId, deliveryTime) {
    // Create confirmation modal
    const confirmationModal = document.createElement('div');
    confirmationModal.className = 'modal';
    confirmationModal.id = 'confirmationModal';
    
    const currentTime = new Date();
    const deliveryTimeDate = new Date(currentTime.getTime() + (deliveryTime * 60000)); // Add delivery time in minutes
    
    confirmationModal.innerHTML = `
        <div class="modal-content">
            <div class="modal-header" style="background: #28a745;">
                <h3><i class="fas fa-check-circle"></i> Payment Successful!</h3>
                <button class="close-btn" onclick="closeConfirmationModal()">
                    <i class="fas fa-times"></i>
                </button>
            </div>
            <div class="payment-content">
                <div class="success-message">
                    <div class="success-icon">
                        <i class="fas fa-check-circle"></i>
                    </div>
                    <h4>Your fuel order has been placed successfully!</h4>
                    <p>Thank you for choosing RSA Project for your fuel delivery.</p>
                </div>
                
                <div class="order-confirmation">
                    <h5>Order Details</h5>
                    <div class="confirmation-details">
                        <p><strong>Order ID:</strong> ${orderId}</p>
                        <p><strong>Fuel Type:</strong> ${orderData.fuelType}</p>
                        <p><strong>Amount:</strong> ${orderData.amount} liters</p>
                        <p><strong>Total Amount:</strong> ${orderData.total}</p>
                        <p><strong>Payment Method:</strong> Online Payment</p>
                        <p><strong>Payment Status:</strong> <span style="color: #28a745;">✓ Paid</span></p>
                    </div>
                </div>
                
                <div class="delivery-info">
                    <h5>Delivery Information</h5>
                    <div class="delivery-details">
                        <p><strong>Estimated Delivery Time:</strong> ${deliveryTime} minutes</p>
                        <p><strong>Expected Delivery:</strong> ${deliveryTimeDate.toLocaleTimeString()}</p>
                        <p><strong>Delivery Location:</strong> ${orderData.location}</p>
                        <p><strong>Contact Number:</strong> ${orderData.phone}</p>
                    </div>
                </div>
                
                <div class="next-steps">
                    <h5>What happens next?</h5>
                    <ul>
                        <li>Our delivery team will contact you within 5 minutes</li>
                        <li>You'll receive SMS confirmation with tracking details</li>
                        <li>Fuel will be delivered to your specified location</li>
                        <li>Payment receipt will be provided upon delivery</li>
                    </ul>
                </div>
                
                <div class="emergency-contact">
                    <p><strong>Need immediate assistance?</strong></p>
                    <p>Call our 24/7 support: <strong>1800-RSA-HELP</strong></p>
                </div>
                
                <button class="submit-btn" onclick="closeConfirmationModal()" style="background: #28a745;">
                    <i class="fas fa-home"></i>
                    Return to Home
                </button>
            </div>
        </div>
    `;
    
    document.body.appendChild(confirmationModal);
    confirmationModal.style.display = 'block';
}

// Close confirmation modal
function closeConfirmationModal() {
    const modal = document.getElementById('confirmationModal');
    if (modal) {
        modal.remove();
        document.body.style.overflow = 'auto';
    }
}

// Handle fuel request
function handleFuelRequest(form) {
    const formData = new FormData(form);
    const data = {
        name: formData.get('name'),
        phone: formData.get('phone'),
        location: formData.get('location'),
        fuelType: formData.get('fuelType'),
        amount: formData.get('amount'),
        vehicle: formData.get('vehicle'),
        paymentMethod: formData.get('paymentMethod'),
        fuelCost: document.getElementById('fuelCost').textContent,
        deliveryFee: document.getElementById('deliveryFee').textContent,
        total: document.getElementById('totalAmount').textContent
    };

    // Validate form
    if (!data.name || !data.phone || !data.location || !data.fuelType || !data.amount || !data.paymentMethod) {
        showMessage('Please fill in all required fields.', 'error');
        return;
    }

    // Check payment method
    if (data.paymentMethod === 'online') {
        // Open payment modal
        closeFuelModal();
        openPaymentModal(data);
    } else if (data.paymentMethod === 'cod') {
        // Process COD order
        processOrder(data, 'cod');
    }
}

// Handle breakdown request
function handleBreakdownRequest(form) {
    const formData = new FormData(form);
    const data = {
        name: formData.get('name'),
        phone: formData.get('phone'),
        location: formData.get('location'),
        issue: formData.get('issue'),
        vehicle: formData.get('vehicle'),
        type: 'breakdown'
    };

    // Validate form
    if (!data.name || !data.phone || !data.location || !data.issue) {
        showMessage('Please fill in all required fields.', 'error');
        return;
    }

    // Process breakdown request
    processBreakdownRequest(data);
}

// Handle contact request
function handleContactRequest(form) {
    const formData = new FormData(form);
    const data = {
        name: formData.get('name'),
        email: formData.get('email'),
        phone: formData.get('phone'),
        message: formData.get('message'),
        type: 'contact'
    };

    // Validate form
    if (!data.name || !data.email || !data.phone || !data.message) {
        showMessage('Please fill in all required fields.', 'error');
        return;
    }

    // Process contact request
    processContactRequest(data);
}

// Handle payment
function handlePayment(form) {
    const formData = new FormData(form);
    const data = {
        cardNumber: formData.get('cardNumber'),
        expiryDate: formData.get('expiryDate'),
        cvv: formData.get('cvv'),
        cardName: formData.get('cardName')
    };

    // Simple validation
    if (!data.cardNumber || !data.expiryDate || !data.cvv || !data.cardName) {
        showMessage('Please fill in all payment details.', 'error');
        return;
    }

    // Simulate payment processing
    showMessage('Processing payment...', 'success');
    
    setTimeout(() => {
        // Get order data from the order summary
        const orderSummary = document.getElementById('orderSummary');
        const orderText = orderSummary.textContent;
        
        // Extract order details (simplified parsing)
        const fuelTypeMatch = orderText.match(/Fuel Type:\s*([^\n]+)/);
        const amountMatch = orderText.match(/Amount:\s*(\d+)/);
        const totalMatch = orderText.match(/Total:\s*₹([\d.]+)/);
        
        const orderData = {
            fuelType: fuelTypeMatch ? fuelTypeMatch[1].trim() : 'Unknown',
            amount: amountMatch ? amountMatch[1] : '0',
            total: totalMatch ? `₹${totalMatch[1]}` : '₹0.00',
            location: document.getElementById('fuelLocation').value,
            phone: document.getElementById('fuelPhone').value,
            name: document.getElementById('fuelName').value
        };
        
        // Calculate delivery time
        const deliveryTime = calculateDeliveryTime(orderData.fuelType, parseInt(orderData.amount));
        
        // Generate order ID
        const orderId = generateOrderId();
        
        // Close payment modal
        closePaymentModal();
        
        // Show payment confirmation
        showPaymentConfirmation(orderData, orderId, deliveryTime);
        
        // Reset fuel form
        document.getElementById('fuelForm').reset();
        calculateTotal();
        
        // Save to localStorage for demo purposes
        saveToLocalStorage({
            ...orderData,
            orderId: orderId,
            deliveryTime: deliveryTime,
            paymentMethod: 'online',
            status: 'confirmed'
        });
    }, 2000);
}

// Process order
function processOrder(data, paymentType) {
    showMessage('Processing your order...', 'success');
    
    setTimeout(() => {
        // Calculate delivery time
        const deliveryTime = calculateDeliveryTime(data.fuelType, parseInt(data.amount));
        
        // Generate order ID
        const orderId = generateOrderId();
        
        if (paymentType === 'cod') {
            showMessage(`Order placed successfully! Order ID: ${orderId}. Pay ₹${data.total.replace('₹', '')} on delivery. Estimated delivery: ${deliveryTime} minutes.`, 'success');
        } else {
            showMessage(`Order placed successfully! Order ID: ${orderId}. Payment completed. Estimated delivery: ${deliveryTime} minutes.`, 'success');
        }
        
        closeFuelModal();
        // Reset form
        document.getElementById('fuelForm').reset();
        calculateTotal();
        
        // Save to localStorage for demo purposes
        saveToLocalStorage({
            ...data,
            orderId: orderId,
            deliveryTime: deliveryTime,
            status: 'confirmed'
        });
    }, 1500);
}

// Process breakdown request
function processBreakdownRequest(data) {
    showMessage('Processing your breakdown request...', 'success');
    
    setTimeout(() => {
        showMessage('Breakdown assistance request submitted! We will contact you shortly. Estimated response time: 10-15 minutes.', 'success');
        closeBreakdownModal();
        
        // Reset form
        document.getElementById('breakdownForm').reset();
        
        // Save to localStorage for demo purposes
        saveToLocalStorage(data);
    }, 1500);
}

// Process contact request
function processContactRequest(data) {
    showMessage('Sending your message...', 'success');
    
    setTimeout(() => {
        showMessage('Message sent successfully! We will get back to you within 4 hours.', 'success');
        
        // Reset form
        document.getElementById('contactForm').reset();
        
        // Save to localStorage for demo purposes
        saveToLocalStorage(data);
    }, 1500);
}

// Save data to localStorage (for demo purposes)
function saveToLocalStorage(data) {
    try {
        const existingData = JSON.parse(localStorage.getItem('rsa_requests') || '[]');
        data.timestamp = new Date().toISOString();
        data.id = Date.now();
        existingData.push(data);
        localStorage.setItem('rsa_requests', JSON.stringify(existingData));
    } catch (error) {
        console.log('Could not save to localStorage:', error);
    }
}

// Show message
function showMessage(message, type = 'success') {
    // Remove existing messages
    const existingMessages = document.querySelectorAll('.message');
    existingMessages.forEach(msg => msg.remove());

    // Create new message
    const messageDiv = document.createElement('div');
    messageDiv.className = `message ${type}`;
    messageDiv.textContent = message;

    // Add to page
    document.body.appendChild(messageDiv);

    // Auto remove after 5 seconds
    setTimeout(() => {
        if (messageDiv.parentNode) {
            messageDiv.remove();
        }
    }, 5000);
}

// Close modals when clicking outside
window.addEventListener('click', function(e) {
    const fuelModal = document.getElementById('fuelModal');
    const breakdownModal = document.getElementById('breakdownModal');
    const paymentModal = document.getElementById('paymentModal');
    const confirmationModal = document.getElementById('confirmationModal');

    if (e.target === fuelModal) {
        closeFuelModal();
    }
    if (e.target === breakdownModal) {
        closeBreakdownModal();
    }
    if (e.target === paymentModal) {
        closePaymentModal();
    }
    if (e.target === confirmationModal) {
        closeConfirmationModal();
    }
});

// Emergency contact function
function callSupport() {
    alert('Calling emergency support: 1800-RSA-HELP');
}

// Format card number input
document.addEventListener('input', function(e) {
    if (e.target.id === 'cardNumber') {
        let value = e.target.value.replace(/\s/g, '').replace(/\D/g, '');
        value = value.replace(/(\d{4})/g, '$1 ').trim();
        e.target.value = value.substring(0, 19);
    }
    
    if (e.target.id === 'expiryDate') {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length >= 2) {
            value = value.substring(0, 2) + '/' + value.substring(2, 4);
        }
        e.target.value = value;
    }
    
    if (e.target.id === 'cvv') {
        e.target.value = e.target.value.replace(/\D/g, '').substring(0, 3);
    }
}); 
