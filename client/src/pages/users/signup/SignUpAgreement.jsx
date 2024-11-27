import InputFactory from '../../../components/input/InputFactory';
import ButtonFactory from '../../../components/button/ButtonFactory';
import pageMove from '../../../assets/js/pageMove';

function SignUpAgreement() {

  return (
    <div className='signup-div signup-agreement'>
        <div className="form-group">
            {InputFactory.CheckBasic("serviceTerms", "서비스 이용 약관에 동의합니다.", "")}
        </div>
        <div className="form-group">
            {InputFactory.CheckBasic("advertisingConsent", "광고 수신에 동의합니다.", "")}
        </div>
        <div className="form-group">
            {ButtonFactory.basic('signup-btn','동의합니다', () => pageMove.paramUrl('http://localhost:3000/signup', '?step=2'), 'w-100')}
        </div>
    </div>
  )
}
export default SignUpAgreement;