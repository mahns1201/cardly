package app.vercel.cardly.common;

public class ApiVersion {
  private ApiVersion() {} // 인스턴스화 방지

  private static final String PATH_PREFIX = "/api";

  public static final String API_V1 = PATH_PREFIX + "/v1";
}
