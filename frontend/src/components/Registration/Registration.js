import {
	Container,
	TextField,
	Grid,
	Typography,
	Button,
	Select,
	InputLabel,
	MenuItem,
} from "@material-ui/core";
import { useForm, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import * as calendar from "../../util/calendar";
import { helperTexts } from "../../properties/registration";
import classes from "./registration.module.css";

const validationSchema = yup
	.object()
	.shape({
		firstName: yup.string().required(),
		lastName: yup.string().required(),
		email: yup.string().email().required(),
		birthYear: yup.number().positive().required(),
		birthMonth: yup.number().positive().max(12).required(),
		birthDay: yup.number().positive().max(31).required(),
		username: yup.string().required(),
		password: yup.string().required().min(8),
	})
	.test(
		"date",
		helperTexts.DATE_FIELD,
		function ({ birthYear, birthMonth, birthDay }) {
			return (
				calendar.isExistingDate(birthYear, birthMonth, birthDay) &&
				calendar.isPastDate(birthYear, birthMonth, birthDay)
			);
		}
	);

function makeMenuItem(value) {
	return (
		<MenuItem value={value} key={value}>
			{value}
		</MenuItem>
	);
}

/**
 * Form for registrating new users.
 * @param {function} onSubmit - The callback function to run on submit
 */
function Registration({ onSubmit }) {
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
			<div className={classes.registration}>
				<Typography variant="h4">Register</Typography>
				<form onSubmit={handleSubmit(onSubmit)}>
					<Grid container spacing={2}>
						<Grid item xs={12} sm={6}>
							<TextField
								label="First name"
								name="firstName"
								inputRef={register}
								fullWidth
								error={
									validationErrors.firstName ? true : false
								}
								helperText={
									validationErrors.firstName
										? helperTexts.FIRST_NAME_FIELD
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
										? helperTexts.LAST_NAME_FIELD
										: null
								}
							/>
						</Grid>
						<Grid item xs={4}>
							<InputLabel id="selecBirthYear">
								Year of birth
							</InputLabel>
							<Controller
								as={
									<Select labelId="selectBirthYear" fullWidth>
										{calendar.getYears().map(makeMenuItem)}
									</Select>
								}
								name="birthYear"
								control={control}
								defaultValue={2020}
							/>
						</Grid>
						<Grid item xs={4}>
							<InputLabel id="selecBirthMonth">
								Month of birth
							</InputLabel>
							<Controller
								as={
									<Select
										labelId="selectBirthMonth"
										fullWidth
									>
										{calendar.getMonths().map(makeMenuItem)}
									</Select>
								}
								name="birthMonth"
								defaultValue={1}
								control={control}
							/>
						</Grid>
						<Grid item xs={4}>
							<InputLabel id="selecBirthDay">
								Day of birth
							</InputLabel>
							<Controller
								as={
									<TextField
										select
										fullWidth
										error={
											validationErrors.date ? true : false
										}
										helperText={
											validationErrors.date
												? helperTexts.DATE_FIELD
												: null
										}
									>
										{calendar.getDays().map(makeMenuItem)}
									</TextField>
								}
								control={control}
								name="birthDay"
								defaultValue={1}
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
										? helperTexts.EMAIL_FIELD
										: null
								}
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
										? helperTexts.USERNAME_FIELD
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
										? helperTexts.PASSWORD_FIELD
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
			</div>
		</Container>
	);
}

export default Registration;
