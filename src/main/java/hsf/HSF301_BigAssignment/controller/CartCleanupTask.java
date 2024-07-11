//package hsf.HSF301_BigAssignment.controller;
//
//import hsf.HSF301_BigAssignment.service.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CartCleanupTask {
//    @Autowired
//    private CartService cartService;
//
//    @Scheduled(fixedRate = 60000) // Cứ 1 phút lại chạy một lần
//    public void cleanUpCarts() {
//        cartService.clearExpiredCarts();
//    }
//}
