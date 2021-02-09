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
	birthYear: yup.number().positive().required(),
	birthMonth: yup.number().min(1).max(12).required(),
	birthDay: yup.number().min(1).max(31).required(),
	username: yup.string().required(),
	password: yup.string().required().min(8),
});

function Registration() {
	const { register, handleSubmit, errors: validationErrors } = useForm({
		resolver: yupResolver(validationSchema),
	});

	function onSubmit(validatedData, event) {
		event.preventDefault();
		event.target.reset();

		const formattedRegistrationData = {
			name: {
				first: validatedData.firstName,
				last: validatedData.lastName,
			},
			email: validatedData.email,
			dateOfBirth: {
				year: validatedData.birthYear,
				month: validatedData.birthMonth,
				day: validatedData.birthDay,
			},
			username: validatedData.username,
			password: validatedData.password,
		};

		console.log(formattedRegistrationData);
	}

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
					<Grid item xs={12}>
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
					<Grid item xs={6} md={4}>
						<TextField
							label="Year of birth"
							name="birthYear"
							inputRef={register}
							error={validationErrors.birthYear ? true : false}
							helperText={
								validationErrors.birthYear
									? "Year of birth is required and should be a number"
									: null
							}
						></TextField>
					</Grid>
					<Grid item xs={6} md={4}>
						<TextField
							label="Month of birth"
							name="birthMonth"
							inputRef={register}
							error={validationErrors.birthMonth ? true : false}
							helperText={
								validationErrors.birthMonth
									? "Month of birth is required and should be a number between 1-12"
									: null
							}
						></TextField>
					</Grid>
					<Grid item xs={6} md={4}>
						<TextField
							label="Day of birth"
							name="birthDay"
							inputRef={register}
							error={validationErrors.birthDay ? true : false}
							helperText={
								validationErrors.birthDay
									? "Day of birth is required and should be a number between 1-31"
									: null
							}
						></TextField>
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
