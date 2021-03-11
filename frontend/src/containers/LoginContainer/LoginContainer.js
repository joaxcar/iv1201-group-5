import { useContext } from "react";
import Login from "../../components/Login/Login";
import endpoints from "../../properties/endpoints";
import { postToAPI } from "../../util/network";
import alertTypes from "../../properties/alerttypes";
import { AlertContext } from "../../App";

/**
 * Container for Login.
 * @param {function} onLogin - The callback function to run on successfull login
 */
function LoginContainer({ onLogin }) {
	const showAlert = useContext(AlertContext);

	function handleFormSubmit(loginDetails, event) {
		event.preventDefault();

		postToAPI(endpoints.AUTHENTICATE, loginDetails)
			.then((response) => {
				event.target.reset();
				onLogin(response.id);
			})
			.catch((error) => {
				showAlert(
					alertTypes.ERROR,
					"Could not login with given credentials"
				);
			});
	}

	return <Login onSubmit={handleFormSubmit} />;
}

export default LoginContainer;
