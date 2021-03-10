import {
	Container,
	TextField,
	Grid,
	Typography,
	Button,
} from "@material-ui/core";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import classes from "./profile.module.css";

const validationSchema = yup.object().shape({
	username: yup.string().required(),
	password: yup.string().required(),
});

/**
 * Profile page for a signed in user
 * @param {number} accountId - The account id for the user
 */
function Profile({ accountId }) {
	const {
		register,
		handleSubmit,
		control,
		errors: validationErrors,
	} = useForm({
		resolver: yupResolver(validationSchema),
	});

	return (
		<Container>
			<div className={classes.profile}>
				<Typography variant="h4">Profile</Typography>
				My account id is: {accountId}
			</div>
		</Container>
	);
}

export default Profile;
