/*
 * @(#)ShellProvider.java 1.0.0 26/06/2014 
 * Copyright (c) 2014 Universidad de las Ciencias Informaticas
 */
package cu.uci.abcd.opac.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cu.uci.abos.api.ui.LayoutProvider;
import cu.uci.abos.api.ui.PlatformContributor;

public class OpacShellProvider {
	public static final String APPLICATION_SHELL = OpacShellProvider.class
			.getName() + "#APPLICATION_SHELL";

	private final PlatformContributor[] uiProviders;
	private final LayoutProvider layoutProvider;
	private final OpacLayoutContextImpl layoutContext;

	public OpacShellProvider(PlatformContributor[] uiProviders,
			LayoutProvider layoutProvider, OpacLayoutContextImpl layoutContext2) {
		this.uiProviders = uiProviders;
		this.layoutProvider = layoutProvider;
		this.layoutContext = layoutContext2;
	}

	public Shell createShell() {
		Shell result = createApplicationShell();
		
		result.setMaximized(true);

		createContent(result);
		layoutShell();

		return result;
	}

	private void layoutShell() {
		layoutProvider.layout(layoutContext);
		layoutContext.layoutShell();
	}

	private void createContent(Shell result) {
		for (PlatformContributor uiProvider : uiProviders) {
			layoutContext.setControl(uiProvider.getID(),
					uiProvider.createUIControl(result));
		}
	}

	private Shell createApplicationShell() {
		Shell result = new Shell(Display.getDefault(), SWT.NO_TRIM);
		result.setMaximized(true);
		result.setData(APPLICATION_SHELL, APPLICATION_SHELL);
		result.setLayout(new FormLayout());
		return result;
	}

	public static boolean isApplicationShell(Shell shell) {
		return shell.getData(APPLICATION_SHELL) != null;
	}

	public static Shell getShellFromDisplay(Display display) {
		Shell[] listsShells = display.getShells();
		for (Shell auxShell : listsShells) {
			if (isApplicationShell(auxShell)) {
				return auxShell;
			}
		}
		return null;
	}
}
