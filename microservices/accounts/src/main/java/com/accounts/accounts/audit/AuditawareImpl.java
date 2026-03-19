package com.accounts.accounts.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditawareImpl implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {
       return Optional.of("ACCOUNTS_MS");
    }

}
