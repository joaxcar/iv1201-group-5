import { useState } from "react";
import Login from "../../components/Login/Login";
import endpoints from "../../properties/endpoints";
import { postToAPI } from "../../util/network";
import alertTypes from "../../properties/alerttypes";
import Alert from "../../components/Alert/Alert";

/**
 * Container for Login.
 */
function LoginContainer({ onLogin }) {
	const [alert, setAlert] = useState({ open: false, type: "", message: "" });

	function showAlert(type, message) {
		setAlert({ open: true, type, message });
		setTimeout(() => setAlert({ ...alert, open: false }), 7000);
	}

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

	return (
		<>
			{alert.open ? (
				<Alert type={alert.type} message={alert.message} />
			) : null}
			<Login onSubmit={handleFormSubmit} />
		</>
	);
}

export default LoginContainer;
