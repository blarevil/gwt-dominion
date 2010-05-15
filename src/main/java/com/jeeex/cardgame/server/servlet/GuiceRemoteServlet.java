package com.jeeex.cardgame.server.servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class GuiceRemoteServlet extends RemoteServiceServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private Injector injector;

	@Inject
	private Provider<EntityManager> emp;

	@Override
	public String processCall(String payload) throws SerializationException {
		try {
			RPCRequest req = RPC.decodeRequest(payload, null, this);
			RemoteService service = getServiceInstance(req.getMethod()
					.getDeclaringClass());
			return RPC.invokeAndEncodeResponse(service, req.getMethod(), req
					.getParameters(), req.getSerializationPolicy());
		} catch (IncompatibleRemoteServiceException ex) {
			log("IncompatibleRemoteServiceException"
					+ " in the processCall(String) method.", ex);
			return RPC.encodeResponseForFailure(null, ex);
		} catch (RuntimeException ex) {
			// other runtime exceptions - at least log them.
			log("Unexpected exception occurred.", ex);
			return RPC.encodeResponseForFailure(null, ex);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().println("OK");
		resp.getWriter().println("em is " + emp.get());
		resp.getWriter().flush();
	}

	@SuppressWarnings("unchecked")
	private RemoteService getServiceInstance(Class serviceClass) {
		return (RemoteService) injector.getInstance(serviceClass);
	}
}
