/**
 * 
 */
package com.speoma.housie.rest.model;

/**
 * @author Seethayya
 *
 */
public class GameType {
	
	public enum RoomType {
		
		ALL_ONCE_PONTS(ResultValueType.POINTS, 1,1,1,1,1),
		NO_LINES_POINTS(ResultValueType.POINTS,1,0,0,0,1),
		ONLY_FULL_POINTS(ResultValueType.POINTS,0,0,0,0,1),
		ONLY_FAST_FIVE_POINTS(ResultValueType.POINTS, 1,0,0,0,0);
		
		private int maxFast5;
		private int maxLine1;
		private int maxLine2;
		private int maxLine3;
		private int full;
		private ResultValueType resultValueType;
		
		/**
		 * @param resultValueType
		 * @param maxFast5
		 * @param maxLine1
		 * @param maxLine2
		 * @param maxLine3
		 * @param maxLine4
		 */
		private RoomType(ResultValueType resultValueType, int maxFast5, int maxLine1, int maxLine2, int maxLine3, int full) {
			this.resultValueType = resultValueType;
			this.maxFast5 = maxFast5;
			this.maxLine1 = maxLine1;
			this.maxLine2 = maxLine2;
			this.maxLine3 = maxLine3;
			this.full = full;
		}

		/**
		 * @return the maxFast5
		 */
		public int getMaxFast5() {
			return maxFast5;
		}

		/**
		 * @return the maxLine1
		 */
		public int getMaxLine1() {
			return maxLine1;
		}

		/**
		 * @return the maxLine2
		 */
		public int getMaxLine2() {
			return maxLine2;
		}

		/**
		 * @return the maxLine3
		 */
		public int getMaxLine3() {
			return maxLine3;
		}

				/**
		 * @return the full
		 */
		public int getFull() {
			return full;
		}

				/**
		 * @return the resultValueType
		 */
		public ResultValueType getResultValueType() {
			return resultValueType;
		}
		
	}
	
	public enum  ResultValueType {
		POINTS,
		OFFER
	}

}
