import { useState } from "react";
import Registration from "../../components/Registration/Registration";
import endpoints from "../../properties/endpoints";
import { postToAPI } from "../../util/network";
import alertTypes from "../../properties/alerttypes";
import Alert from "../../components/Alert/Alert";

function formatDetails(registrationDetails) {
	return {
		name: {
			first: registrationDetails.firstName,
			last: registrationDetails.lastName,
		},
		email: registrationDetails.email,
		dateOfBirth: {
			year: registrationDetails.birthYear,
			month: registrationDetails.birthMonth,
			day: registrationDetails.birthDay,
		},
		username: registrationDetails.username,
		password: registrationDetails.password,
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
