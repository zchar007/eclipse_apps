package com.astar;

import java.awt.Color;

public class AStar {

	
	public static int MAP_SIZE = 800;// ��ͼ��С
	public static int NODE_SIZE = 200;// ��ͼ��ÿ����λ��С

	public static int STEP_MAX = 1000;
	public static boolean STRAIGHT = true;// �Ƿ���ֱ����
	public static boolean SKEW = true;// �Ƿ���б����

	public static final int NODE_GROUND = 0;// �յ�
	public static final int NODE_START = 1;// ��ʼ��
	public static final int NODE_END = 2;// ������
	public static final int NODE_OBS_0 = 3;// �ϰ���
	public static final int NODE_OBS_1 = 4;// �ϰ���
	public static final int NODE_OBS_2 = 5;// �ϰ���
	public static final int NODE_OBS_3 = 6;// �ϰ���
	public static final int NODE_PATH = 7;// ·��
	public static final int NODE_HIS_WAY = 8;// ·��
	public static int NOW_DRAW_TYPE = AStar.NODE_START;

	public static Color COLOR_LINE = Color.WHITE;
	public static Color COLOR_GROUND = Color.RED;
	public static Color COLOR_START = Color.GREEN;
	public static Color COLOR_END = Color.BLUE;
	public static Color COLOR_OBS_0 = Color.ORANGE;
	public static Color COLOR_OBS_1 = Color.LIGHT_GRAY;
	public static Color COLOR_OBS_2 = Color.GRAY;
	public static Color COLOR_OBS_3 = Color.BLACK;
	public static Color COLOR_PATH = Color.YELLOW;
	public static Color COLOR_HIS_WAY = Color.PINK;

	public static Color NOW_DRAW_COLOR = AStar.COLOR_START;

	public static final int INSPIRE_TYPE_CLASSIC = 0;//
	public static final int INSPIRE_TYPE_OLD = 1;//
	public static final int INSPIRE_TYPE_FUDGE = 2;//
	public static int NOW_INSPIRE_TYPE = AStar.INSPIRE_TYPE_OLD;// ·��

	public static long DRAW_PATH_INTERVAL = 10;// ����·������ʱ��ʱ������Ĭ��20����

	public static final double THROW_LEVEL_SKEW_RATIO = 1.4;// ��Ҫб����
	
	public static final double THROW_LEVEL_START = 100;// ��ʼ�㣬�ɱ�Ӱ�������߼�������Ӱ����
	public static final double THROW_LEVEL_END = 1.0;// �����ɱ�Ϊ0�����������׵���
	public static final double THROW_LEVEL_0 = 0.1;// �ر��ͨ��
	public static final double THROW_LEVEL_GROUND = 1;// ����ͨ��
	public static final double THROW_LEVEL_1 = 5;// ����ͨ��
	public static final double THROW_LEVEL_2 = 10;// �����޷�ͨ��
	public static final double THROW_LEVEL_3 = Double.MAX_VALUE;// ȷ���޷�ͨ��

	/**
	 * ��ֵ̬ ֵ�𶯣�ǣ���������߼�
	 */
	public static final int ORDER_SELF = 0;
	public static final int ORDER_UP = 1;
	public static final int ORDER_DOWN = 2;
	public static final int ORDER_RIGHT = 3;
	public static final int ORDER_LEFT = 4;
	public static final int ORDER_RIGHT_UP = 31;
	public static final int ORDER_LEFT_UP = 41;
	public static final int ORDER_RIGHT_DOWN = 32;
	public static final int ORDER_LEFT_DOWN = 42;

	// �쳣����
	public static final int EXCEPTION_WARN = 0;//
	public static final int EXCEPTION_INFO = 1;//
	public static final int EXCEPTION_ERROR = 2;//
	public static final int EXCEPTION_QUESTION = 3;//


	public static Color getColor(int now_draw_type) {
		switch (now_draw_type) {
		case AStar.NODE_START: {
			return AStar.COLOR_START;
		}
		case AStar.NODE_END: {
			return AStar.COLOR_END;
		}
		case AStar.NODE_OBS_0: {
			return AStar.COLOR_OBS_0;
		}
		case AStar.NODE_OBS_1: {
			return AStar.COLOR_OBS_1;
		}
		case AStar.NODE_OBS_2: {
			return AStar.COLOR_OBS_2;
		}
		case AStar.NODE_OBS_3: {
			return AStar.COLOR_OBS_3;
		}
		case AStar.NODE_GROUND: {
			return AStar.COLOR_GROUND;
		}
		case AStar.NODE_PATH: {
			return AStar.COLOR_PATH;
		}
		case AStar.NODE_HIS_WAY: {
			return AStar.COLOR_HIS_WAY;
		}
		default: {
			return AStar.COLOR_GROUND;

		}
		}

	}
}
