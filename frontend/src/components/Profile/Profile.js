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
 * Form for logging in to the application.
 * @param {function} onSubmit - The callback function to run on submit
 */
function Profile({ onSubmit }) {
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
				<Typography variant="h4">Login</Typography>
				<form onSubmit={handleSubmit(onSubmit)}>
					<Grid container spacing={2}>
						<Grid item xs={12}>
							<TextField
								label="Username"
								name="username"
								inputRef={register}
								fullWidth
								error={validationErrors.username ? true : false}
								helperText={
									validationErrors.username
										? "Username is required to login"
										: null
								}
							/>
						</Grid>
						<Grid item xs={12}>
							<TextField
								label="Password"
								name="password"
								inputRef={register}
								fullWidth
								error={validationErrors.password ? true : false}
								helperText={
									validationErrors.password
										? "Password is required to login"
										: null
								}
							/>
						</Grid>
						<Grid item>
							<Button variant="contained" type="submit">
								Login
							</Button>
						</Grid>
					</Grid>
				</form>
			</div>
		</Container>
	);
}

export default Profile;
