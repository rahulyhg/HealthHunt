package in.healthhunt.model.notification;

import java.util.List;

public class NotificationData {
	private int offset;
	private int totalNotification;
	private int limit;
	private List<NotificationsItem> notifications;

	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return offset;
	}

	public void setTotalNotification(int totalNotification){
		this.totalNotification = totalNotification;
	}

	public int getTotalNotification(){
		return totalNotification;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setNotifications(List<NotificationsItem> notifications){
		this.notifications = notifications;
	}

	public List<NotificationsItem> getNotifications(){
		return notifications;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"offset = '" + offset + '\'' + 
			",totalNotification = '" + totalNotification + '\'' + 
			",limit = '" + limit + '\'' + 
			",notifications = '" + notifications + '\'' + 
			"}";
		}
}