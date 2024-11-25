import InputFactory from '../../../components/input/InputFactory';
import ButtonFactory from '../../../components/button/ButtonFactory';
import signUpEvent from '../../../assets/js/signUpEvent';

function SignUpData() {

  return (
    <div className='signup-div signup-data'>
      <div className="signup-form">
        <form id="signup-form">
          <div className="form-group">
            {InputFactory.basic('ecUsersEmail', '이메일을 입력하세요', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.password('ecUsersPassword', '비밀번호를 입력하세요', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.password('ecUsersPasswordCheck', '비밀번호 확인', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.basic('ecUsersName', '이름을 입력하세요', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.basic('ecUsersPhone', '전화번호를 입력하세요', "", 'input-field', true)}
          </div>
          <div className="form-group">
            {InputFactory.basic('ecUsersBirthDate', '생년월일 입력하세요', "", 'input-field')}
          </div>
          <div className="form-group">
            {InputFactory.basic('ecUsersReferralCode', '추천인 코드를 입력하세요', "", 'input-field')}
          </div>
        </form>
        <div className="form-group">
          {ButtonFactory.basic('signup-btn','회원가입', () => signUpEvent.createUser(), 'w-100')}
        </div>
      </div>
    </div>
  )
}
export default SignUpData;