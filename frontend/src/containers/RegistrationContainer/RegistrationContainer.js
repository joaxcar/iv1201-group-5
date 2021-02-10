import { useState } from "react";
import Registration from "../../components/Registration/Registration";
import endpoints from "../../properties/endpoints";
import { postToAPI } from "../../util/network";
import alertTypes from "../../properties/alerttypes";
import Alert from "../../components/Alert/Alert";

function formatDetails(registrationDetails) {
	const MONTH_ADJUSTER = 1;
	return {
		name: {
			first: registrationDetails.firstName,
			last: registrationDetails.lastName,
		},
		email: registrationDetails.email,
		dateOfBirth: {
			year: registrationDetails.dateOfBirth.getFullYear(),
			month: registrationDetails.dateOfBirth.getMonth() + MONTH_ADJUSTER,
			day: registrationDetails.dateOfBirth.getDate(),
		},
		username: registrationDetails.username,
		password: registrationDetails.password,
	};
}

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
				<Alert type={alert.type}>{alert.message}</Alert>
			) : null}
			<Registration onSubmit={handleFormSubmit} />
		</>
	);
}

export default RegistrationContainer;
