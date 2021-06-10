package com.example.TicketingSystem.configuration;



import java.util.Optional;

//public class AuditorAwareImpl implements AuditorAware<String> {
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
//        }
//
//        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
//            return Optional.ofNullable("ROLE_ANONYMOUS");
//        }
//
//        return Optional.ofNullable(((User) authentication.getPrincipal()).getUsername());
//    }
//}
