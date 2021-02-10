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

const validationSchema = yup.object().shape({
	firstName: yup.string().required(),
	lastName: yup.string().required(),
	email: yup.string().email().required(),
	dateOfBirth: yup.date().max(new Date()).required(),
	username: yup.string().required(),
	password: yup.string().required().min(8),
});

function Registration({ onSubmit }) {
	const { register, handleSubmit, errors: validationErrors } = useForm({
		resolver: yupResolver(validationSchema),
	});

	return (
		<Container maxWidth="sm">
			<Typography variant="h4">Register</Typography>
			<form onSubmit={handleSubmit(onSubmit)}>
				<Grid container spacing={2}>
					<Grid item xs={12} sm={6}>
						<TextField
							label="First name"
							name="firstName"
							inputRef={register}
							fullWidth
							error={validationErrors.firstName ? true : false}
							helperText={
								validationErrors.firstName
									? "First name is required"
									: null
							}
						/>
					</Grid>
					<Grid item xs={12} sm={6}>
						<TextField
							label="Last name"
							name="lastName"
							inputRef={register}
							fullWidth
							error={validationErrors.lastName ? true : false}
							helperText={
								validationErrors.lastName
									? "Last name is required"
									: null
							}
						/>
					</Grid>
					<Grid item xs={12} sm={6}>
						<TextField
							label="E-mail address"
							name="email"
							inputRef={register}
							fullWidth
							error={validationErrors.email ? true : false}
							helperText={
								validationErrors.email
									? "A valid e-mail address is required"
									: null
							}
						/>
					</Grid>
					<Grid item xs={12} sm={6}>
						<TextField
							label="Date of birth yyyy-mm-dd"
							name="dateOfBirth"
							inputRef={register}
							fullWidth
							type="date"
							error={validationErrors.dateOfBirth ? true : false}
							helperText={
								validationErrors.dateOfBirth
									? "Date should be in the format yyyy-mm-dd and not be later than today"
									: null
							}
							InputLabelProps={{ shrink: true }}
						/>
					</Grid>
					<Grid item xs={12}>
						<TextField
							label="Username"
							name="username"
							inputRef={register}
							fullWidth
							error={validationErrors.username ? true : false}
							helperText={
								validationErrors.username
									? "Username is required"
									: null
							}
						/>
					</Grid>
					<Grid item xs={12}>
						<TextField
							label="Password"
							name="password"
							inputRef={register}
							type="password"
							fullWidth
							error={validationErrors.password ? true : false}
							helperText={
								validationErrors.password
									? "Password is required and should be at least 8 characters"
									: null
							}
						/>
					</Grid>
					<Grid item>
						<Button variant="contained" type="submit">
							Register
						</Button>
					</Grid>
				</Grid>
			</form>
		</Container>
	);
}

export default Registration;
