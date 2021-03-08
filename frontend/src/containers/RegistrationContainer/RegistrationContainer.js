import { useState } from "react";
import Registration from "../../components/Registration/Registration";
import endpoints from "../../properties/endpoints";
import { postToAPI } from "../../util/network";
import alertTypes from "../../properties/alerttypes";
import Alert from "../../components/Alert/Alert";

function formatDetails({
	firstName,
	lastName,
	email,
	birthYear,
	birthMonth,
	birthDay,
	username,
	password,
}) {
	const dateOfBirth = `${birthYear}-${String(birthMonth).padStart(
		2,
		"0"
	)}-${String(birthDay).padStart(2, "0")}`;

	return {
		name: {
			first: firstName,
			last: lastName,
		},
		email: email,
		dateOfBirth,
		username: username,
		password: password,
	};
}

/**
 * Container for Registration.
 */
function RegistrationContainer() {
	const [alert, setAlert] = useState({ open: false, type: "", message: "" });

	function showAlert(type, message) {
		setAlert({ open: true, type, message });
		setTimeout(() => setAlert({ ...alert, open: false }), 7000);
	}

	function handleFormSubmit(registrationDetails, event) {
		event.preventDefault();
		event.target.reset();

		const formattedRegistrationDetails = formatDetails(registrationDetails);

		postToAPI(endpoints.ACCOUNTS, formattedRegistrationDetails)
			.then((response) =>
				showAlert(alertTypes.SUCCESS, "Registration was successful")
			)
			.catch((error) =>
				showAlert(alertTypes.ERROR, "Something went wrong")
			);
	}

	return (
		<>
			{alert.open ? (
				<Alert type={alert.type} message={alert.message} />
			) : null}
			<Registration onSubmit={handleFormSubmit} />
		</>
	);
}

export default RegistrationContainer;
