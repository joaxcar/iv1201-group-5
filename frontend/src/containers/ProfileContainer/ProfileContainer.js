import { useState } from "react";
import Profile from "../../components/Profile/Profile";
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
 * Container for Profile.
 * * @param {number} accountId - The account id for the user
 */
function ProfileContainer({ accountId }) {
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
				showAlert(alertTypes.SUCCESS, "Login successful");
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
			<Profile accountId={accountId} />
		</>
	);
}

export default ProfileContainer;
