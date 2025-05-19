package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class PreferCategoryHandler extends GeneralException {
  public PreferCategoryHandler(BaseErrorCode errorCode) {
      super(errorCode);
  }
}
