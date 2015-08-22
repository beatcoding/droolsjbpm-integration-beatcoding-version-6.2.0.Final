package org.kie.remote.services.jms.request;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import org.jbpm.kie.services.impl.audit.ServicesAwareAuditEventBuilder;
import org.jbpm.services.cdi.RequestScopedBackupIdentityProvider;
import org.kie.internal.identity.IdentityProvider;
import org.kie.remote.services.jms.RequestMessageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * See the {@link ServicesAwareAuditEventBuilder} bean for more context.
 * </p>
 * This bean provides a {@link RequestScopedBackupIdentityProvider} instance 
 * for JMS requests, which have a Request scope but for which the "normally" 
 * provided ("normally" as in with REST requests) {@link IdentityProvider} 
 * is not available. This due to vagaries of the code, as always. 
 */
@RequestScoped
public class BackupIdentityProviderProducer {

    private static final Logger logger = LoggerFactory.getLogger(RequestMessageBean.class);
    
    private RequestScopedBackupIdentityProvider identityProvider = null;
    
    public RequestScopedBackupIdentityProvider createBackupIdentityProvider(String commandUser, List<String> roles) {
        logger.debug( "Creating identity provider for user: {}", commandUser);
        if( commandUser == null ) { 
            commandUser = RequestScopedBackupIdentityProvider.UNKNOWN;
        }
        final String nameValue = commandUser;
        
        this.identityProvider =  new RequestScopedBackupIdentityProvider() {
            
            private String name = nameValue;
            
            @Override
            public String getName() {
                return name;
            }
        };
        
        return this.identityProvider;
    }
    
    @Produces
    @RequestScoped
    public RequestScopedBackupIdentityProvider getJmsRequestScopeIdentityProvider() {
        if (this.identityProvider != null) {
            logger.debug( "Producing backup identity bean for user: {}" , this.identityProvider.getName() );
            return this.identityProvider;
        } else {
            logger.debug( "Unknown user during JMS request" );
            // in case there is none set return dummy one as @RequestScoped producers cannot return null
            return new RequestScopedBackupIdentityProvider() {
                
                @Override
                public String getName() {
                    return RequestScopedBackupIdentityProvider.UNKNOWN;
                }

            };
        }
    }

}
