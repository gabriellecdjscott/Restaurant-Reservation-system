package src;
 public abstract class BasePerson {

	protected String name;
	private boolean publish;
	private String date;
	private String time;
	private String rFrame;
	private String typeR;
	private int numPerson;
	
		public BasePerson(String name,String date, String time, String rFrame, String typeR, int numPerson )
		{
			this.name =name;
			this.date=date;
			this.time = time;
			this.rFrame= rFrame;
			this.typeR = typeR;
			this.numPerson = numPerson;
			 
		}
		
		
		/** 
		 * @return String
		 */
		public String getDate()
		{
			return date;
		}
		
		
		/** 
		 * @return String
		 */
		public String getTime()
		{
			return time;
		}

		
		/** 
		 * @return String
		 */
		public String getReservationFrame()
		{
			return rFrame;
		}

		
		/** 
		 * @return String
		 */
		public String getTypeReservation()
		{
			return typeR;
		}

		
		/** 
		 * @return int
		 */
		public int getNumPerson()
		{
			return numPerson;
		}

		public abstract String getName();

		
} 
