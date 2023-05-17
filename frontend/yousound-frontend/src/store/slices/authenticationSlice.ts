interface AuthenticationState {
    authenticated: boolean;
    userId: string | null;
    token: string | null;
    refreshToken: string | null;
    error: string | null;
  }
  
  const initialState: AuthenticationState = {
    authenticated: false,
    userId: null,
    token: null,
    refreshToken: null,
    error: null,
  };
  
  type AuthenticationAction =
    | {
        type: "AUTHENTICATION_SUCCESS";
        payload: { userId: string; token: string; refreshToken: string };
      }
    | { type: "AUTHENTICATION_FAILURE"; payload: string }
    | { type: "LOGOUT" };
  
  const authenticationReducer = (
    state: AuthenticationState = initialState,
    action: AuthenticationAction
  ): AuthenticationState => {
    switch (action.type) {
      case "AUTHENTICATION_SUCCESS":
        return {
          ...state,
          authenticated: true,
          userId: action.payload.userId,
          token: action.payload.token,
          refreshToken: action.payload.refreshToken,
          error: null,
        };
      case "AUTHENTICATION_FAILURE":
        return {
          ...state,
          authenticated: false,
          userId: null,
          token: null,
          refreshToken: null,
          error: action.payload,
        };
      case "LOGOUT":
        return initialState;
      default:
        return state;
    }
  };
  
  export default authenticationReducer;