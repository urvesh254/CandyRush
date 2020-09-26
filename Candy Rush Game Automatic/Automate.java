class Automate implements Runnable
{
	private Thread t;
	static CandyRush cr;

	Automate(CandyRush cr){
		this.cr=cr;
		t = new Thread(this);

		/*
		Here we can not write the thread start method because this thread is start by the executorservices.
		So, no need to write the thread start method.
		 */
	}

	public void run(){
		
		int boulX = cr.boul.getX()+100;
		int boulY = cr.boul.getY();
		int ballX = cr.xCoordinate.pollLast();
		int fw = cr.getBounds().width;

		// System.out.println("boulx = "+boulX+" ballX = "+ballX);

		try{
			if(boulX<=ballX){
				//Boul goes right.
				for(int i=boulX; i<=ballX && i+100<fw; i+=25){
					cr.boul.setBounds(i-100,boulY,200,80);
					Thread.sleep(44);
				}
			}
			else{
				//Boul goes left.
				for(int i=boulX; i>=ballX && i-100>0; i-=25){
					cr.boul.setBounds(i-100,boulY,200,80);
					Thread.sleep(44);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
}