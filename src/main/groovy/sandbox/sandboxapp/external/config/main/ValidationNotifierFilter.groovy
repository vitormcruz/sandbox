package sandbox.sandboxapp.external.config.main

import sandbox.validationNotification.ApplicationValidationNotifier

import javax.servlet.*

class ValidationNotifierFilter implements Filter{

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
        //Nothing to do
    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ApplicationValidationNotifier.createCurrentListOfListeners()

        chain.doFilter(request,response)

        ApplicationValidationNotifier.destroyCurrentListOfListeners()
    }

    @Override
    void destroy() {
        //Nothing to do
    }
}
