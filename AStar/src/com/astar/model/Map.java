package com.astar.model;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

import com.astar.AStar;
import com.astar.AStarException;

public class Map extends JPanel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Vector<Node> nodes = new Vector<>();

	/**
	 * Create the panel.
	 */
	public Map() {
		this.setPreferredSize(new Dimension(AStar.MAP_SIZE, AStar.MAP_SIZE));
		setLayout(null);
		nodes = new Vector<>();
		this.removeAll();
		int every = AStar.MAP_SIZE / AStar.NODE_SIZE;

		int index = 0;
		for (int i = 0; i < every; i++) {
			for (int j = 0; j < every; j++) {
				Node node = new Node(index++);
				node.setLocation(i * AStar.NODE_SIZE, j * AStar.NODE_SIZE);
				node.toGround();
				nodes.addElement(node);
				this.add(node);
			}
		}

	}

	@Override
	public void repaint() {
		super.repaint();

	}

	public void clearPath() {
		Node.canDraw = true;
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).clearPath();
		}
		repaint();
	}

	public void clear() {
		Node.canDraw = true;
		nodes = new Vector<>();
		this.removeAll();
		int every = AStar.MAP_SIZE / AStar.NODE_SIZE;
		Node.startNode = null;
		Node.endNode = null;
		int index = 0;
		for (int i = 0; i < every; i++) {
			for (int j = 0; j < every; j++) {
				Node node = new Node(index++);
				node.setLocation(i * AStar.NODE_SIZE, j * AStar.NODE_SIZE);
				node.toGround();
				nodes.addElement(node);
				this.add(node);
			}
		}
		repaint();
	}

	public static Vector<Node> getNodes() {
		return nodes;
	}

	public Node[] getAdjacent(Node now) {
		Node[] node_array = new Node[8];

		int number = now.getNumber();
		int every = AStar.MAP_SIZE / AStar.NODE_SIZE;

		if (AStar.STRAIGHT) {
			// ��
			if (number - every >= 0) {
				node_array[0] = Map.nodes.get(number - every);
			}
			// ��
			if (number + every < Map.nodes.size()) {
				node_array[1] = Map.nodes.get(number + every);
			}
			// �� ����ߵ�û�����
			if (number - 1 >= 0 && number % every != 0) {
				node_array[2] = Map.nodes.get(number - 1);
			}
			// �� ���ұߵ�û���ҵ�
			if ((number + 1) % every != 0 && number + 1 < Map.nodes.size()) {
				node_array[3] = Map.nodes.get(number + 1);

			}
		}
		if (AStar.SKEW) {
			// ���� ����ߵ�û�����ϵ�
			if (number % every != 0 && number - every - 1 >= 0) {
				node_array[4] = Map.nodes.get(number - every - 1);
			}
			// ���� ����ߵ�û�����µ�
			if (number % every != 0 && number + every - 1 < Map.nodes.size()) {
				node_array[5] = Map.nodes.get(number + every - 1);
			}
			// ���� ���ұߵ�û������
			if ((number + 1) % every != 0 && number - every + 1 >= 0) {
				node_array[6] = Map.nodes.get(number - every + 1);
			}
			// ���� ���ұߵ�û������
			if ((number + 1) % every != 0 && number + every + 1 < Map.nodes.size()) {
				node_array[7] = Map.nodes.get(number + every + 1);
			}
		}

		return node_array;
	}

	/**
	 * TOFINE �����ԭ���ߵ��߼��������Ż�<br>
	 * <br>
	 * �������µ�ͼƬ����ͼ�������<br>
	 * 
	 * end, fineway, fineway, normalway,.......<br>
	 * normalway, fineway, fineway, normalway.........<br>
	 * normalway, normalway, normalway, normalway.........<br>
	 * normalway, normalway, normalway, start.........<br>
	 * 
	 * @throws AStarException
	 * 
	 */
	public Node getLowestAdjacent(Node now) throws AStarException {
		Node next[] = getAdjacent(now);
		Node small = next[0];
		double dist = Double.MAX_VALUE;

		Node start = null;
		for (int i = 0; i < next.length; i++) {
			if (next[i] != null) {

				double nextDist = next[i].getDistFromStart();
				// ���ӹ��ǿ�ʼ�ڵ㣬��ֱ�ӷ��أ���ֹ�ڿ�ʼ�ڵ��ǰһ����������ѭ��
				// ���һȦ�����յ㣬���յ�ʹ˵���һͬһ�л�ͬһ�У���ôֱ�ӷ��ؽܿˣ�������б���ϣ��п���ֱ���߲�������õ�

				
				int position = next[i].getPosition(now);
				if (next[i].isStart()) {
					start = next[i];
					if (position < 10) {
						return start;
					}
				}
				if (!next[i].isHisWay() && !next[i].isPath()) {
					continue;
				}
				double c = now.getThrowCost(next[i]);
				double distFromStart = next[i].getDistFromStart();

				if (c >= 0) {
					nextDist += c;
				}
				// }
				if (nextDist < dist && nextDist >= 0) {
					small = next[i];
					if (c > 0) {
						dist = distFromStart + c;
					} else {
						dist = distFromStart;
					}

				}
			}
		}
		// ��ǰ�� now,��Ѱ������һ�� small
		if (null != start && Node.getStartNode().equals(start) && !small.equals(start)) {
			double startToSmall = now.getThrowCost(start);
			double startToSmallToNow = small.getThrowCost(start) + now.getThrowCost(small);

			if (startToSmallToNow >= startToSmall) {
				return start;
			}

		}
		return small;
	}
}
