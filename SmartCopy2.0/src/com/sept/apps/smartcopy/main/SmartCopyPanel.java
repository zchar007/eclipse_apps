package com.sept.apps.smartcopy.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.sept.apps.smartcopy.beanfactory.BeanFactory;
import com.sept.jui.input.text.STextPane;
import com.sept.jui.util.TextUtil;

public class SmartCopyPanel extends STextPane {
	private BeanFactory beanFactory;
	private HashMap<String, String[]> hmNames;
	private HashMap<JMenuItem, String> hmKeys = new HashMap<>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	public SmartCopyPanel() throws Exception {
		super();
		this.addPop();

	}

	private void addPop() throws Exception {
		JMenu menu = new JMenu("专用复制");
		beanFactory = new BeanFactory();
		beanFactory.reload();

		hmNames = beanFactory.getNames();
		for (String key : hmNames.keySet()) {
			JMenuItem item = new JMenuItem(hmNames.get(key)[0]);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String key = hmKeys.get(e.getSource());
					try {
						TextUtil.sendStringToClipboard(
								beanFactory.getBean(key).entry(SmartCopyPanel.this.getSelectText()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			item.setToolTipText(hmNames.get(key)[1]);
			hmKeys.put(item, key);
			menu.add(item);
		}
		this.addMenu(menu);
		this.getJTextPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					menu.setEnabled(SmartCopyPanel.this.isCanCopy());
				}
			}
		});
	}

}
