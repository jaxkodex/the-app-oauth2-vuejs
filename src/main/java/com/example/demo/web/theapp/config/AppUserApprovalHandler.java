package com.example.demo.web.theapp.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;

import java.util.Collection;

public class AppUserApprovalHandler extends ApprovalStoreUserApprovalHandler {
    private boolean userApprovalStore = true;
    private ClientDetailsService clientDetailsService;

    public void setUserApprovalStore(boolean userApprovalStore) {
        this.userApprovalStore = userApprovalStore;
    }

    @Override
    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
        super.setClientDetailsService(clientDetailsService);
    }

    @Override
    public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        boolean approved = false;

        if (userApprovalStore) {
            authorizationRequest = super.checkForPreApproval(authorizationRequest, userAuthentication);
            approved = authorizationRequest.isApproved();
        } else {
            if (clientDetailsService != null) {
                Collection<String> requestedScopes = authorizationRequest.getScope();
                try {
                    ClientDetails details = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
                    for (String scope : requestedScopes) {
                        if (details.isAutoApprove(scope)) {
                            approved = true;
                            break;
                        }
                    }
                } catch (Exception e) {}
            }
        }
        authorizationRequest.setApproved(approved);
        return authorizationRequest;
    }
}
