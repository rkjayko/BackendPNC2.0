package co.com.cidenet.pnc.exceptions;

public class AnnouncementNotFoundException extends ControlledException {
  /** */
  private static final long serialVersionUID = 4351108743874380836L;

  public AnnouncementNotFoundException() {
    super();
  }

  public AnnouncementNotFoundException(String message) {
    super(message);
  }
}
