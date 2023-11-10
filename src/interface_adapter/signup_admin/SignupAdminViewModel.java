package interface_adapter.signup_admin;

public class SignupAdminViewModel extends ViewModel{
    private SignupAdminState state = new SignupAdminState();

    public void setState(SignupAdminState state1){}

    public SignupAdminState getState(){
        return state;
    }

    public void firePropertyChanged(){}

    public String getViewName(){return "";}
}
