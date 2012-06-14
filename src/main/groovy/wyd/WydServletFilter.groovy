package wyd

import java.io.IOException
import java.util.List;

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.RequestDispatcher
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class WydServletFilter implements Filter {

	private static final long serialVersionUID = 1L

	final Logger _log = LoggerFactory.getLogger(WydServletFilter.class)

	final List<String> publicUrls = [
		'/echo.groovy',
		'/wydEcho.groovy',
		'/about.groovy',
		'/wydAbout.groovy',
		'/wydIndex.groovy',
		'/login.groovy',
		'/wydLogin.groovy',
		'/acmAuthenticate.groovy',
		'/logout.groovy',
		'/wydLogout.groovy',
		'/mobile/wydLogin.groovy',
		'/mobile/acmAuthenticate.groovy',
		'/mobile/wydLogout.groovy',
		'/mobile/wydAbout.groovy',
		'/webConsole.groovy',
		'/regexpConsole.groovy',
		'/networkConsole.groovy',
		'/instaCalc.groovy',
		'/commandLineFu.groovy',
		'/a/init.groovy'
	]

	final List<String> messageIds = [
		'warningMessage',
		'infoMessage',
		'successMessage',
		'errorMessage'
	]
	protected FilterConfig filterConfig

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig
	}

	@Override
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
	FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request

		this.messageIds.each { messageId ->
			def message = req.getParameter(messageId)
			req.setAttribute(messageId, message)
			if(_log.isDebugEnabled()) {
				_log.debug('Message = {}', message)
			}
			//System.out.println("Message = $message")
		}

		def callbackUrl = req.getParameter('callbackUrl')
		if (callbackUrl != null) {
			request.setAttribute('callbackUrl', callbackUrl)
			if (_log.isDebugEnabled()) {
				_log.debug('Callback URL = {}', callbackUrl)
			}
		}

		HttpSession session = req.getSession(true)
		def user = session.getAttribute('user')
		def uri = req.getRequestURI()
		//System.out.println("URI = $uri")
		if(!this.publicUrls.contains(uri) && !req.getRequestURI().startsWith('/t/') && user == null) {
			String s = uri.startsWith('/mobile') ? '/mobile/wydLogin.groovy' : '/wydLogin.groovy'
			ServletContext scontext = this.filterConfig.getServletContext()
			RequestDispatcher rd = scontext.getRequestDispatcher(s)
			s = "Please login to access this page '$uri'"
			request.setAttribute('errorMessage', s)
			request.setAttribute('callbackUrl', uri)
			rd.forward(request, response);
			return
		}
		chain.doFilter(request, response)
	}
}
